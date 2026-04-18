const mongoose = require("mongoose");

const milkEntrySchema = new mongoose.Schema(
  {
    customerId: { type: mongoose.Schema.Types.ObjectId, ref: "Customer", required: true, index: true },
    entryDate: { type: String, required: true, index: true },
    session: { type: String, enum: ["MORNING", "EVENING"], required: true },
    quantityLiters: { type: Number, required: true, min: 0 },
    pricePerLiter: { type: Number, required: true, min: 0 },
    amount: { type: Number, required: true, min: 0 },
    createdBy: { type: mongoose.Schema.Types.ObjectId, ref: "User", required: true },
    updatedBy: { type: mongoose.Schema.Types.ObjectId, ref: "User", default: null },
  },
  { timestamps: true }
);

milkEntrySchema.index({ customerId: 1, entryDate: 1, session: 1 }, { unique: true });

module.exports = mongoose.model("MilkEntry", milkEntrySchema);
