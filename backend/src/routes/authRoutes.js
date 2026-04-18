const express = require("express");
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const User = require("../models/User");
const Customer = require("../models/Customer");
const { jwtSecret } = require("../config/env");
const { requireAuth, requireRole } = require("../middleware/auth");

const router = express.Router();

function getPermissionsForRole(role) {
  const isSuperUser = role === "SUPER_USER";
  const isAdmin = role === "ADMIN";

  return {
    canCreateAdmin: isSuperUser,
    canCreateStaff: isSuperUser || isAdmin,
    canCreateCustomer: isSuperUser || isAdmin,
    canUpdateAnyUser: isSuperUser,
    canDeleteAnyUser: isSuperUser,
    canUpdateAssignedUsers: isSuperUser || isAdmin,
    canDeleteAssignedUsers: isSuperUser || isAdmin,
    canViewAllCustomers: isSuperUser,
    canUpdateAnyCustomer: isSuperUser,
    canDeleteAnyCustomer: isSuperUser,
    canUpdateAssignedCustomers: isSuperUser || isAdmin,
    canDeleteAssignedCustomers: isSuperUser || isAdmin,
    canViewAdmins: isSuperUser,
  };
}

router.post("/login", async (req, res, next) => {
  try {
    const { username, password } = req.body;
    if (!username || !password) {
      return res.status(400).json({ message: "username and password are required" });
    }

    const user = await User.findOne({ username: username.trim(), isActive: true });
    if (!user) {
      return res.status(401).json({ message: "Invalid credentials" });
    }

    const ok = await bcrypt.compare(password, user.passwordHash);
    if (!ok) {
      return res.status(401).json({ message: "Invalid credentials" });
    }

    const token = jwt.sign(
      {
        sub: String(user._id),
        username: user.username,
        fullName: user.fullName,
        role: user.role,
      },
      jwtSecret,
      { expiresIn: "12h" }
    );

    return res.json({
      token,
      user: {
        id: String(user._id),
        username: user.username,
        fullName: user.fullName,
        role: user.role,
      },
    });
  } catch (err) {
    next(err);
  }
});

// Get all admins (Super user only)
router.get("/admins", requireAuth, requireRole("SUPER_USER"), async (_req, res, next) => {
  try {
    const admins = await User.find({ role: "ADMIN" })
      .select("_id username fullName role isActive createdAt updatedAt")
      .lean();

    if (admins.length === 0) {
      return res.json([]);
    }

    const adminIds = admins.map((admin) => admin._id);
    const [userCounts, customerCounts] = await Promise.all([
      User.aggregate([
        { $match: { createdBy: { $in: adminIds } } },
        { $group: { _id: "$createdBy", count: { $sum: 1 } } },
      ]),
      Customer.aggregate([
        { $match: { createdBy: { $in: adminIds } } },
        { $group: { _id: "$createdBy", count: { $sum: 1 } } },
      ]),
    ]);

    const userCountByAdmin = new Map(
      userCounts.map((item) => [String(item._id), item.count])
    );
    const customerCountByAdmin = new Map(
      customerCounts.map((item) => [String(item._id), item.count])
    );

    res.json(
      admins.map((admin) => ({
        id: String(admin._id),
        username: admin.username,
        fullName: admin.fullName,
        role: admin.role,
        isActive: admin.isActive,
        createdAt: admin.createdAt,
        updatedAt: admin.updatedAt,
        permissions: getPermissionsForRole(admin.role),
        assignedUsersCount: userCountByAdmin.get(String(admin._id)) || 0,
        assignedCustomersCount: customerCountByAdmin.get(String(admin._id)) || 0,
      }))
    );
  } catch (err) {
    next(err);
  }
});

// Get complete admin details (Super user only)
router.get("/admins/:id", requireAuth, requireRole("SUPER_USER"), async (req, res, next) => {
  try {
    const admin = await User.findOne({ _id: req.params.id, role: "ADMIN" })
      .select("_id username fullName role isActive createdAt updatedAt createdBy")
      .populate("createdBy", "_id username fullName role")
      .lean();

    if (!admin) {
      return res.status(404).json({ message: "Admin not found" });
    }

    const [assignedUsers, assignedCustomers] = await Promise.all([
      User.find({ createdBy: admin._id })
        .select("_id username fullName role isActive createdAt updatedAt")
        .sort({ createdAt: -1 })
        .lean(),
      Customer.find({ createdBy: admin._id })
        .select("_id name phone address type pricePerLiter createdAt updatedAt")
        .sort({ createdAt: -1 })
        .lean(),
    ]);

    return res.json({
      id: String(admin._id),
      username: admin.username,
      fullName: admin.fullName,
      role: admin.role,
      isActive: admin.isActive,
      createdAt: admin.createdAt,
      updatedAt: admin.updatedAt,
      createdBy: admin.createdBy
        ? {
            id: String(admin.createdBy._id),
            username: admin.createdBy.username,
            fullName: admin.createdBy.fullName,
            role: admin.createdBy.role,
          }
        : null,
      permissions: getPermissionsForRole(admin.role),
      assignedUsersCount: assignedUsers.length,
      assignedCustomersCount: assignedCustomers.length,
      assignedUsers: assignedUsers.map((user) => ({
        id: String(user._id),
        username: user.username,
        fullName: user.fullName,
        role: user.role,
        isActive: user.isActive,
        createdAt: user.createdAt,
        updatedAt: user.updatedAt,
      })),
      assignedCustomers: assignedCustomers.map((customer) => ({
        id: String(customer._id),
        name: customer.name,
        phone: customer.phone,
        address: customer.address,
        type: customer.type,
        pricePerLiter: customer.pricePerLiter,
        createdAt: customer.createdAt,
        updatedAt: customer.updatedAt,
      })),
    });
  } catch (err) {
    next(err);
  }
});

module.exports = router;
