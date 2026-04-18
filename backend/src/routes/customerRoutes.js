const express = require("express");
const Customer = require("../models/Customer");
const { requireAuth } = require("../middleware/auth");
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

function canManageCustomer(user, customer) {
  if (!user || !customer) {
    return false;
  }

  if (user.role === "SUPER_USER") {
    return true;
  }

  if (user.role === "ADMIN") {
    return isOwnedByUser(customer, user.sub);
  }

  return false;
}

router.get("/", async (req, res, next) => {
  try {
    const currentUser = req.effectiveUser || req.user;
    const { q, type } = req.query;
    const filter = {};

    // Super users can view all customers, admins/staff are scoped to their own records.
    if (currentUser.role !== "SUPER_USER") {
      filter.createdBy = currentUser.sub;
    }

    if (q) {
      filter.$or = [
        { name: { $regex: q, $options: "i" } },
        { phone: { $regex: q, $options: "i" } },
      ];
    }
    if (type) {
      filter.type = type;
    }

    const customers = await Customer.find(filter).sort({ name: 1 });
    res.json(customers);
  } catch (err) {
    next(err);
  }
});

router.post("/", async (req, res, next) => {
  try {
    const currentUser = req.effectiveUser || req.user;
    if (!["ADMIN", "SUPER_USER"].includes(currentUser.role)) {
      return res.status(403).json({ message: "Only admin and super user can create customers" });
    }

    const { name, phone, address, type, pricePerLiter, buyingPricePerLiter } = req.body;
    if (!name || !phone || !address || !type) {
      return res.status(400).json({ message: "name, phone, address, type are required" });
    }

    const parsedPrice = Number(
      pricePerLiter !== undefined ? pricePerLiter : buyingPricePerLiter
    );
    const safePrice = Number.isFinite(parsedPrice) && parsedPrice >= 0 ? parsedPrice : 0;

    console.log(`\n📥 POST /customers - Adding customer: ${name}`);

    // Use sync service to create customer
    const customer = await queueDbOperation("CREATE", "customers", async () => {
      return await Customer.create({
        name,
        phone,
        address,
        pricePerLiter: safePrice,
        type,
        createdBy: currentUser.sub,
        updatedBy: null,
      });
    });

    console.log(`📊 Customer creation result:`, customer);

    // Create audit log (don't wait for it)
    const recordId = customer._id || customer.recordId || "pending";
    createAuditLog({
      tableName: "customers",
      recordId: recordId,
      action: "CREATE",
      userId: currentUser.sub,
      details: `Customer added: ${name}`,
    }).catch(err => console.error("Audit log error:", err.message));

    // Handle both synced and queued responses
    if (customer.queued) {
      return res.status(201).json({
        id: recordId,
        name,
        phone,
        address,
        pricePerLiter: safePrice,
        type,
        syncStatus: "QUEUED_FOR_SYNC",
        message: "Customer will be synced to database",
      });
    }

    // Return synced customer
    res.status(201).json({
      id: String(customer._id),
      name: customer.name,
      phone: customer.phone,
      address: customer.address,
      pricePerLiter: customer.pricePerLiter ?? 0,
      type: customer.type,
      syncStatus: "SYNCED",
      message: "Customer synced successfully",
    });
  } catch (err) {
    console.error("Customer creation error:", err);
    next(err);
  }
});

router.put("/:id", async (req, res, next) => {
  try {
    const currentUser = req.effectiveUser || req.user;
    if (!["ADMIN", "SUPER_USER"].includes(currentUser.role)) {
      return res.status(403).json({ message: "Only admin and super user can update customers" });
    }

    const { name, phone, address, type, pricePerLiter, buyingPricePerLiter } = req.body;
    const { id } = req.params;

    const existingCustomer = await Customer.findById(id);
    if (!existingCustomer) {
      return res.status(404).json({ message: "Customer not found" });
    }

    if (!canManageCustomer(currentUser, existingCustomer)) {
      return res.status(403).json({ message: "You can only update customers assigned to you" });
    }

    const parsedPrice = Number(
      pricePerLiter !== undefined ? pricePerLiter : buyingPricePerLiter
    );
    const safePrice = Number.isFinite(parsedPrice) && parsedPrice >= 0 ? parsedPrice : undefined;

    const updatePayload = { updatedBy: currentUser.sub };
    if (name !== undefined) updatePayload.name = name;
    if (phone !== undefined) updatePayload.phone = phone;
    if (address !== undefined) updatePayload.address = address;
    if (type !== undefined) updatePayload.type = type;
    if (safePrice !== undefined) {
      updatePayload.pricePerLiter = safePrice;
    }

    if (Object.keys(updatePayload).length === 1) {
      return res.status(400).json({ message: "No valid fields provided for update" });
    }

    // Use sync service to update customer
    const customer = await queueDbOperation("UPDATE", "customers", async () => {
      return await Customer.findByIdAndUpdate(
        id,
        updatePayload,
        { new: true }
      );
    }, { requireImmediateSync: true });

    await createAuditLog({
      tableName: "customers",
      recordId: id,
      action: "UPDATE",
      userId: currentUser.sub,
      details: `Customer updated: ${customer.name}`,
    });

    res.json(customer);
  } catch (err) {
    next(err);
  }
});

// Delete customer
router.delete("/:id", async (req, res, next) => {
  try {
    const currentUser = req.effectiveUser || req.user;
    if (!["ADMIN", "SUPER_USER"].includes(currentUser.role)) {
      return res.status(403).json({ message: "Only admin and super user can delete customers" });
    }

    const { id } = req.params;

    const customer = await Customer.findById(id);
    if (!customer) return res.status(404).json({ message: "Customer not found" });

    if (!canManageCustomer(currentUser, customer)) {
      return res.status(403).json({ message: "You can only delete customers assigned to you" });
    }

    console.log(`\n🗑️  DELETE /customers/:id - Deleting customer: ${id}`);

    await queueDbOperation("DELETE", "customers", async () => {
      return await Customer.findByIdAndDelete(id);
    }, { requireImmediateSync: true });

    await createAuditLog({
      tableName: "customers",
      recordId: id,
      action: "DELETE",
      userId: currentUser.sub,
      details: `Customer deleted: ${customer.name}`,
    }).catch(err => console.error("Audit log error:", err.message));

    res.json({
      message: "Customer deleted successfully",
      id,
      name: customer.name,
    });
  } catch (err) {
    next(err);
  }
});

module.exports = router;
