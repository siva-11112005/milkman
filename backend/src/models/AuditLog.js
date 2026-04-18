const mongoose = require("mongoose");

const auditLogSchema = new mongoose.Schema(
  {
    tableName: { type: String, required: true, index: true },
    recordId: { type: String, required: true, index: true },
    action: { type: String, required: true, enum: ["CREATE", "UPDATE", "DELETE"] },
    userId: { type: mongoose.Schema.Types.ObjectId, ref: "User", required: true },
    details: { type: String, required: true },
  },
  { timestamps: true }
);

auditLogSchema.index({ tableName: 1, recordId: 1, createdAt: -1 });

module.exports = mongoose.model("AuditLog", auditLogSchema);
