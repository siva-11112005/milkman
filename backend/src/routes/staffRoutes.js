const express = require("express");
const bcrypt = require("bcryptjs");
const User = require("../models/User");
const { requireAuth, requireRole } = require("../middleware/auth");
const { createAuditLog } = require("../services/auditService");
const { queueDbOperation } = require("../utils/dbOperations");

const router = express.Router();

router.use(requireAuth);

function isOwnedByUser(record, userId) {
  if (!record || !record.createdBy || !userId) {
    return false;
  }

  return String(record.createdBy) === String(userId);
}

router.get("/", requireRole("ADMIN", "SUPER_USER"), async (_req, res, next) => {
  try {
    const filter = { role: "STAFF" };

    // Admins can only view staff they created.
    if (_req.user.role === "ADMIN") {
      filter.createdBy = _req.user.sub;
    }

    const staff = await User.find(filter).select("_id username fullName role isActive createdAt");
    res.json(
      staff.map((u) => ({
        id: String(u._id),
        username: u.username,
        fullName: u.fullName,
        role: u.role,
        isActive: u.isActive,
        createdAt: u.createdAt,
      }))
    );
  } catch (err) {
    next(err);
  }
});

router.post("/", requireRole("ADMIN", "SUPER_USER"), async (req, res, next) => {
  try {
    const { username, password, fullName } = req.body;
    if (!username || !password || !fullName) {
      return res.status(400).json({ message: "username, password, fullName are required" });
    }

    const trimmedUsername = username.trim();
    const existingUser = await User.findOne({ username: trimmedUsername });
    
    // If user already exists, return success (idempotent)
    if (existingUser) {
      console.log(`\n📥 POST /staff - User already exists: ${trimmedUsername}`);
      return res.status(201).json({
        id: String(existingUser._id),
        username: existingUser.username,
        fullName: existingUser.fullName,
        role: existingUser.role,
        syncStatus: "ALREADY_EXISTS",
        message: "User already exists",
      });
    }

    const passwordHash = await bcrypt.hash(password, 10);
    
    // Super user creates ADMIN, regular ADMIN creates STAFF
    const roleToCreate = req.user.role === "SUPER_USER" ? "ADMIN" : "STAFF";
    
    console.log(`\n📥 POST /staff - Creating ${roleToCreate}: ${trimmedUsername}`);

    // Use sync service to create user
    const user = await queueDbOperation("CREATE", "users", async () => {
      return await User.create({
        username: trimmedUsername,
        passwordHash,
        fullName: fullName.trim(),
        role: roleToCreate,
        isActive: true,
        createdBy: req.user.sub,
      });
    });

    console.log(`📊 User creation result:`, user);

    // Create audit log (don't wait for it)
    createAuditLog({
      tableName: "users",
      recordId: user._id || "pending",
      action: "CREATE",
      userId: req.user.sub,
      details: `${roleToCreate} created: ${user.username}`,
    }).catch(err => console.error("Audit log error:", err.message));

    res.status(201).json({
      id: String(user._id || "pending"),
      username: user.username,
      fullName: user.fullName,
      role: user.role,
      syncStatus: user.queued ? "QUEUED_FOR_SYNC" : "SYNCED",
      message: user.queued ? "User will be synced to database" : "User synced successfully",
    });
  } catch (err) {
    console.error("Staff creation error:", err);
    next(err);
  }
});

// Update staff/admin (Super user can update anyone, Admin can update their staff)
router.put("/:id", requireRole("ADMIN", "SUPER_USER"), async (req, res, next) => {
  try {
    const { fullName, isActive } = req.body;
    const userId = req.params.id;

    const user = await User.findById(userId);
    if (!user) return res.status(404).json({ message: "User not found" });

    // Super user can update anyone.
    // Admin can only update STAFF users they created.
    if (req.user.role === "ADMIN") {
      if (user.role !== "STAFF") {
        return res.status(403).json({ message: "Admins can only update staff users" });
      }

      if (!isOwnedByUser(user, req.user.sub)) {
        return res.status(403).json({ message: "You can only update staff users you created" });
      }
    }

    const updateData = {};
    if (fullName) updateData.fullName = fullName.trim();
    if (isActive !== undefined) updateData.isActive = isActive;
    updateData.updatedBy = req.user.sub;

    console.log(`\n📝 PUT /staff/:id - Updating user: ${userId}`);

    const updated = await queueDbOperation("UPDATE", "users", async () => {
      return await User.findByIdAndUpdate(userId, updateData, { new: true });
    }, { requireImmediateSync: true });

    await createAuditLog({
      tableName: "users",
      recordId: userId,
      action: "UPDATE",
      userId: req.user.sub,
      details: `User updated: ${user.username}`,
    }).catch(err => console.error("Audit log error:", err.message));

    if (!updated) {
      return res.status(404).json({ message: "User not found" });
    }

    res.json({
      id: String(updated._id),
      username: updated.username,
      fullName: updated.fullName,
      role: updated.role,
      isActive: updated.isActive,
      syncStatus: "SYNCED",
    });
  } catch (err) {
    console.error("Staff update error:", err);
    next(err);
  }
});

// Delete staff/admin (Super user can delete anyone, Admin can only delete their staff)
router.delete("/:id", requireRole("ADMIN", "SUPER_USER"), async (req, res, next) => {
  try {
    const userId = req.params.id;

    const user = await User.findById(userId);
    if (!user) return res.status(404).json({ message: "User not found" });

    // Super user can delete anyone.
    // Admin can only delete STAFF users they created.
    if (req.user.role === "ADMIN") {
      if (user.role !== "STAFF") {
        return res.status(403).json({ message: "Admins can only delete staff users" });
      }

      if (!isOwnedByUser(user, req.user.sub)) {
        return res.status(403).json({ message: "You can only delete staff users you created" });
      }
    }

    // Prevent deleting self
    if (userId === req.user.sub) {
      return res.status(403).json({ message: "You cannot delete your own account" });
    }

    console.log(`\n🗑️  DELETE /staff/:id - Deleting user: ${userId}`);

    await queueDbOperation("DELETE", "users", async () => {
      return await User.findByIdAndDelete(userId);
    }, { requireImmediateSync: true });

    await createAuditLog({
      tableName: "users",
      recordId: userId,
      action: "DELETE",
      userId: req.user.sub,
      details: `User deleted: ${user.username}`,
    }).catch(err => console.error("Audit log error:", err.message));

    res.json({
      message: "User deleted successfully",
      id: userId,
      username: user.username,
    });
  } catch (err) {
    console.error("Staff deletion error:", err);
    next(err);
  }
});

module.exports = router;
