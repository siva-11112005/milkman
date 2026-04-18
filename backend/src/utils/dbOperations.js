const syncService = require("../services/syncService");
const { ensureDbConnection } = require("../config/db");

/**
 * Creates an operation object for the sync service
 * @param {string} type - Operation type (create, update, delete)
 * @param {string} collection - Collection name
 * @param {Function} executeFunction - Async function that performs the operation
 */
function createOperation(type, collection, executeFunction) {
  return {
    id: `${collection}_${type}_${Date.now()}_${Math.random()
      .toString(36)
      .slice(2, 10)}`,
    type,
    collection,
    execute: executeFunction,
  };
}

/**
 * Queues an operation through the sync service
 * Handles real-time + retry logic automatically
 * Returns operation result on success, queued info on failure (will retry automatically)
 */
async function queueDbOperation(type, collection, executeFunction, options = {}) {
  const {
    requireImmediateSync = false,
    ensureConnection = true,
  } = options;

  try {
    console.log(`\n📝 Starting ${type} operation on ${collection}`);

    if (requireImmediateSync) {
      console.log(`🔒 ${type} requires immediate MongoDB sync`);
      await ensureDbConnection();
      const result = await executeFunction();
      console.log(`✅ ${type} operation completed with immediate sync on ${collection}`);
      return result;
    }

    const operation = createOperation(type, collection, async () => {
      if (ensureConnection) {
        await ensureDbConnection();
      }

      return await executeFunction();
    });

    const result = await syncService.queueOperation(operation);
    console.log(`✅ ${type} operation succeeded immediately on ${collection}`);
    return result;
  } catch (error) {
    if (requireImmediateSync) {
      error.status = error.status || 503;
      throw error;
    }

    console.error(
      `⚠️  ${type} operation queued for sync on ${collection}`
    );
    console.error(`   Error Message: ${error.message}`);
    console.error(`   Error Code: ${error.code}`);
    console.error(`   Error Name: ${error.name}`);
    
    if (error.response) {
      console.error(`   Response Status: ${error.response.status}`);
      console.error(`   Response Data:`, error.response.data);
    }
    
    // Don't throw - return error info so caller knows it's queued
    // The sync service will retry automatically
    return {
      queued: true,
      type,
      collection,
      message: "Operation queued for automatic sync",
      error: error.message,
      errorCode: error.code,
      errorName: error.name,
    };
  }
}

/**
 * Wrapper for User create operations
 */
async function createUser(userData) {
  const User = require("../models/User");
  return queueDbOperation("CREATE", "users", async () => {
    return await User.create(userData);
  });
}

/**
 * Wrapper for User update operations
 */
async function updateUser(userId, updateData) {
  const User = require("../models/User");
  return queueDbOperation("UPDATE", "users", async () => {
    return await User.findByIdAndUpdate(userId, updateData, { new: true });
  });
}

/**
 * Wrapper for Customer create operations
 */
async function createCustomer(customerData) {
  const Customer = require("../models/Customer");
  return queueDbOperation("CREATE", "customers", async () => {
    return await Customer.create(customerData);
  });
}

/**
 * Wrapper for Customer update operations
 */
async function updateCustomer(customerId, updateData) {
  const Customer = require("../models/Customer");
  return queueDbOperation("UPDATE", "customers", async () => {
    return await Customer.findByIdAndUpdate(customerId, updateData, {
      new: true,
    });
  });
}

/**
 * Wrapper for MilkEntry create operations
 */
async function createMilkEntry(entryData) {
  const MilkEntry = require("../models/MilkEntry");
  return queueDbOperation("CREATE", "milkentries", async () => {
    return await MilkEntry.create(entryData);
  });
}

/**
 * Wrapper for MilkEntry update operations
 */
async function updateMilkEntry(entryId, updateData) {
  const MilkEntry = require("../models/MilkEntry");
  return queueDbOperation("UPDATE", "milkentries", async () => {
    return await MilkEntry.findByIdAndUpdate(entryId, updateData, {
      new: true,
    });
  });
}

module.exports = {
  createOperation,
  queueDbOperation,
  createUser,
  updateUser,
  createCustomer,
  updateCustomer,
  createMilkEntry,
  updateMilkEntry,
};
