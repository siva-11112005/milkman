const express = require("express");
const MilkEntry = require("../models/MilkEntry");
const { requireAuth } = require("../middleware/auth");
const { createAuditLog } = require("../services/auditService");
const { isLocked } = require("../utils/lock");
const { queueDbOperation } = require("../utils/dbOperations");

const router = express.Router();

router.use(requireAuth);

router.get("/", async (req, res, next) => {
  try {
    const { date, customerId } = req.query;
    const filter = {};
    if (date) filter.entryDate = date;
    if (customerId) filter.customerId = customerId;

    const rows = await MilkEntry.find(filter)
      .populate("customerId", "name type phone")
      .sort({ entryDate: -1, session: 1, createdAt: -1 });

    const mapped = rows.map((r) => ({
      id: String(r._id),
      customerId: String(r.customerId?._id || ""),
      customerName: r.customerId?.name || "",
      customerType: r.customerId?.type || "",
      entryDate: r.entryDate,
      session: r.session,
      quantityLiters: r.quantityLiters,
      pricePerLiter: r.pricePerLiter,
      amount: r.amount,
      createdAt: r.createdAt,
      locked: isLocked(r.createdAt),
    }));

    res.json(mapped);
  } catch (err) {
    next(err);
  }
});

router.post("/", async (req, res, next) => {
  try {
    const { customerId, entryDate, session, quantityLiters, pricePerLiter } = req.body;
    if (!customerId || !entryDate || !session || quantityLiters == null || pricePerLiter == null) {
      return res.status(400).json({
        message: "customerId, entryDate, session, quantityLiters, pricePerLiter are required",
      });
    }

    const amount = Number(quantityLiters) * Number(pricePerLiter);
    
    console.log(`\n📥 POST /milk - Adding milk entry for customer ${customerId}`);

    // Use sync service to create milk entry
    const row = await queueDbOperation("CREATE", "milkentries", async () => {
      return await MilkEntry.create({
        customerId,
        entryDate,
        session,
        quantityLiters,
        pricePerLiter,
        amount,
        createdBy: req.user.sub,
        updatedBy: null,
      });
    });

    console.log(`📊 Milk entry result:`, row);

    // Create audit log (don't wait for it)
    const recordId = row._id || row.recordId || "pending";
    createAuditLog({
      tableName: "milk_entries",
      recordId: recordId,
      action: "CREATE",
      userId: req.user.sub,
      details: `Milk entry added for customer ${customerId} ${entryDate} ${session}`,
    }).catch(err => console.error("Audit log error:", err.message));

    // Handle both synced and queued responses
    if (row.queued) {
      return res.status(201).json({
        id: recordId,
        customerId,
        entryDate,
        session,
        quantityLiters,
        pricePerLiter,
        amount,
        syncStatus: "QUEUED_FOR_SYNC",
        message: "Milk entry will be synced to database",
      });
    }

    // Return synced milk entry
    res.status(201).json({
      id: String(row._id),
      customerId: String(row.customerId),
      entryDate: row.entryDate,
      session: row.session,
      quantityLiters: row.quantityLiters,
      pricePerLiter: row.pricePerLiter,
      amount: row.amount,
      syncStatus: "SYNCED",
      message: "Milk entry synced successfully",
    });
  } catch (err) {
    if (err && err.code === 11000) {
      return res.status(409).json({ message: "Duplicate entry for customer/date/session" });
    }
    console.error("Milk entry creation error:", err);
    next(err);
  }
});

router.put("/:id", async (req, res, next) => {
  try {
    const { quantityLiters, pricePerLiter } = req.body;
    if (quantityLiters == null || pricePerLiter == null) {
      return res.status(400).json({ message: "quantityLiters and pricePerLiter are required" });
    }

    const row = await MilkEntry.findById(req.params.id);
    if (!row) return res.status(404).json({ message: "Entry not found" });
    if (isLocked(row.createdAt) && req.user.role !== "SUPER_USER") {
      return res.status(403).json({ message: "Record locked after 1 hour" });
    }

    // Use sync service to update milk entry
    row.quantityLiters = Number(quantityLiters);
    row.pricePerLiter = Number(pricePerLiter);
    row.amount = row.quantityLiters * row.pricePerLiter;
    row.updatedBy = req.user.sub;
    
    const updated = await queueDbOperation("UPDATE", "milkentries", async () => {
      return await row.save();
    }, { requireImmediateSync: true });

    await createAuditLog({
      tableName: "milk_entries",
      recordId: row._id,
      action: "UPDATE",
      userId: req.user.sub,
      details: "Milk entry updated",
    });

    res.json(updated);
  } catch (err) {
    next(err);
  }
});

// Delete milk entry
router.delete("/:id", async (req, res, next) => {
  try {
    const { id } = req.params;

    const row = await MilkEntry.findById(id);
    if (!row) return res.status(404).json({ message: "Entry not found" });
    
    if (isLocked(row.createdAt) && req.user.role !== "SUPER_USER") {
      return res.status(403).json({ message: "Record locked after 1 hour" });
    }

    console.log(`\n🗑️  DELETE /milk/:id - Deleting milk entry: ${id}`);

    await queueDbOperation("DELETE", "milkentries", async () => {
      return await MilkEntry.findByIdAndDelete(id);
    }, { requireImmediateSync: true });

    await createAuditLog({
      tableName: "milk_entries",
      recordId: id,
      action: "DELETE",
      userId: req.user.sub,
      details: `Milk entry deleted`,
    }).catch(err => console.error("Audit log error:", err.message));

    res.json({
      message: "Milk entry deleted successfully",
      id,
    });
  } catch (err) {
    next(err);
  }
});

module.exports = router;
