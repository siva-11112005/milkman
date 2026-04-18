const express = require("express");
const MilkEntry = require("../models/MilkEntry");
const { requireAuth, requireRole } = require("../middleware/auth");

const router = express.Router();

router.use(requireAuth);

router.get("/dashboard", requireRole("ADMIN"), async (req, res, next) => {
  try {
    const date = req.query.date;
    const month = req.query.month;

    const [dailyAgg, monthlyAgg, byType] = await Promise.all([
      date
        ? MilkEntry.aggregate([
            { $match: { entryDate: date } },
            {
              $group: {
                _id: null,
                totalQuantity: { $sum: "$quantityLiters" },
                totalAmount: { $sum: "$amount" },
              },
            },
          ])
        : Promise.resolve([]),
      month
        ? MilkEntry.aggregate([
            { $match: { entryDate: { $regex: `^${month}` } } },
            {
              $group: {
                _id: null,
                totalQuantity: { $sum: "$quantityLiters" },
                totalAmount: { $sum: "$amount" },
              },
            },
          ])
        : Promise.resolve([]),
      month
        ? MilkEntry.aggregate([
            { $match: { entryDate: { $regex: `^${month}` } } },
            {
              $lookup: {
                from: "customers",
                localField: "customerId",
                foreignField: "_id",
                as: "customer",
              },
            },
            { $unwind: "$customer" },
            {
              $group: {
                _id: "$customer.type",
                totalQuantity: { $sum: "$quantityLiters" },
                totalAmount: { $sum: "$amount" },
              },
            },
            { $sort: { _id: 1 } },
          ])
        : Promise.resolve([]),
    ]);

    res.json({
      daily: dailyAgg[0] || { totalQuantity: 0, totalAmount: 0 },
      monthly: monthlyAgg[0] || { totalQuantity: 0, totalAmount: 0 },
      byType,
    });
  } catch (err) {
    next(err);
  }
});

router.get("/monthly", async (req, res, next) => {
  try {
    const { month } = req.query;
    if (!month) return res.status(400).json({ message: "month is required, e.g. 2026-04" });

    const rows = await MilkEntry.aggregate([
      { $match: { entryDate: { $regex: `^${month}` } } },
      {
        $lookup: {
          from: "customers",
          localField: "customerId",
          foreignField: "_id",
          as: "customer",
        },
      },
      { $unwind: "$customer" },
      {
        $group: {
          _id: "$customerId",
          customerName: { $first: "$customer.name" },
          customerType: { $first: "$customer.type" },
          totalQuantity: { $sum: "$quantityLiters" },
          totalAmount: { $sum: "$amount" },
        },
      },
      { $sort: { customerName: 1 } },
    ]);

    if (req.user.role !== "ADMIN") {
      return res.json(
        rows.map((r) => ({
          customerId: String(r._id),
          customerName: r.customerName,
          customerType: r.customerType,
          totalQuantity: r.totalQuantity,
        }))
      );
    }

    return res.json(
      rows.map((r) => ({
        customerId: String(r._id),
        customerName: r.customerName,
        customerType: r.customerType,
        totalQuantity: r.totalQuantity,
        totalAmount: r.totalAmount,
      }))
    );
  } catch (err) {
    next(err);
  }
});

module.exports = router;
