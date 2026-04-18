const AuditLog = require("../models/AuditLog");
const { queueDbOperation } = require("../utils/dbOperations");

async function createAuditLog({ tableName, recordId, action, userId, details }) {
  try {
    // Use sync service to queue audit log creation
    await queueDbOperation("CREATE", "auditlogs", async () => {
      return await AuditLog.create({
        tableName,
        recordId: String(recordId),
        action,
        userId,
        details,
      });
    });
  } catch (error) {
    console.error("Audit log sync error:", error.message);
    // Audit logs aren't critical - don't throw if they fail
  }
}

module.exports = { createAuditLog };
