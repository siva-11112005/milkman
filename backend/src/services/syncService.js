const { syncRetryInterval, maxRetryAttempts } = require("../config/env");
const { isInternetAvailable, waitForInternet } = require("../utils/networkUtils");

class SyncService {
  constructor() {
    this.operationQueue = [];
    this.isSyncing = false;
    this.retryIntervals = new Map();
    this.internetCheckIntervals = new Map();
    this.syncStatus = {
      isSynced: false,
      lastSyncTime: null,
      failedOperations: 0,
      successfulOperations: 0,
    };
  }

  createOperationId(operation) {
    if (operation.id) {
      return operation.id;
    }

    return `${operation.collection}_${operation.type}_${Date.now()}_${Math.random()
      .toString(36)
      .slice(2, 10)}`;
  }

  /**
   * Queues an operation for sync
   * Tries real-time first, then retries every 1 min if fails
   */
  async queueOperation(operation) {
    const operationWithId = {
      ...operation,
      id: this.createOperationId(operation),
    };

    try {
      console.log(
        `⏱️  Attempting real-time operation: ${operationWithId.type} on ${operationWithId.collection}`
      );

      // Try real-time operation
      const result = await operationWithId.execute();
      this.syncStatus.successfulOperations++;
      console.log(
        `✅ Real-time operation succeeded: ${operationWithId.type} on ${operationWithId.collection}`
      );
      return result;
    } catch (error) {
      console.error(
        `❌ Real-time operation failed: ${operationWithId.type}`,
        error.message
      );
      console.error(`📋 Full error stack:`, error);
      console.error(`🔍 Error code:`, error.code);
      console.error(`🔍 Error name:`, error.name);
      this.syncStatus.failedOperations++;
      this.syncStatus.isSynced = false;

      const queueItem = {
        id: operationWithId.id,
        operation: operationWithId,
        retryCount: 0,
        addedAt: new Date(),
      };

      // Queue for retry every 1 minute
      this.operationQueue.push(queueItem);

      // Start retry mechanism for this operation
      this.startRetryMechanism(queueItem);
      throw error;
    }
  }

  /**
   * Starts 1-minute retry interval for failed operations
   */
  startRetryMechanism(queueItem) {
    const operationKey = queueItem.id;

    if (this.retryIntervals.has(operationKey)) {
      console.log(`ℹ️  Retry already scheduled for ${operationKey}`);
      return;
    }

    const intervalId = setInterval(async () => {
      await this.retryFailedOperation(operationKey);
    }, syncRetryInterval); // 1 minute

    this.retryIntervals.set(operationKey, intervalId);
    console.log(
      `⏰ Retry scheduled every 1 min for ${operationKey} (Max ${maxRetryAttempts} attempts)`
    );
  }

  /**
   * Retries failed operations every 1 minute
   * After 5 attempts, checks internet and waits if offline
   */
  async retryFailedOperation(operationId) {
    const queueItem = this.operationQueue.find((item) => item.id === operationId);

    if (!queueItem) {
      console.log(`ℹ️  Operation already completed: ${operationId}`);
      this.stopRetryMechanism(operationId);
      return;
    }

    try {
      if (queueItem.retryCount >= maxRetryAttempts) {
        // After 5 attempts, check internet connectivity
        console.warn(
          `⚠️  Max retry attempts reached (${maxRetryAttempts}), checking internet...`
        );

        const isOnline = await isInternetAvailable();

        if (!isOnline) {
          console.error(
            `🌐 No internet connection detected. Waiting for internet...`
          );

          // Wait for internet to come back
          const internetRestored = await waitForInternet();

          if (internetRestored) {
            // Reset retry count and continue
            queueItem.retryCount = 0;
            console.log(
              `✅ Internet restored! Resetting retry count for ${queueItem.operation.type}`
            );
            console.log(
              `🔄 Will resume retrying every 1 minute for ${queueItem.operation.type}`
            );
            return; // Continue with next interval
          } else {
            console.error(
              `❌ Failed to restore internet connection. Giving up on ${queueItem.operation.type}.`
            );
            this.removeFromQueue(operationId);
            this.stopRetryMechanism(operationId);
            return;
          }
        } else {
          console.log(
            `✅ Internet is available, continuing retries for ${queueItem.operation.type}`
          );
          queueItem.retryCount = 0; // Reset to continue retrying
          return;
        }
      }

      queueItem.retryCount++;
      console.log(
        `🔄 Retry attempt ${queueItem.retryCount}/${maxRetryAttempts} for ${queueItem.operation.type}`
      );

      // Attempt retry
      await queueItem.operation.execute();
      console.log(
        `✅ Retry successful: ${queueItem.operation.type} on ${queueItem.operation.collection}`
      );
      this.syncStatus.successfulOperations++;
      this.removeFromQueue(operationId);
      this.stopRetryMechanism(operationId);
    } catch (error) {
      console.error(
        `⚠️  Retry failed attempt ${queueItem.retryCount}: ${queueItem.operation.type}`
      );
      console.error(`   Error: ${error.message}`);
      console.error(`   Code: ${error.code}`);
      console.error(`   Name: ${error.name}`);
    }
  }

  /**
   * Removes operation from queue
   */
  removeFromQueue(operationId) {
    this.operationQueue = this.operationQueue.filter(
      (item) => item.id !== operationId
    );
    console.log(`🗑️  Removed operation ${operationId} from retry queue`);
  }

  /**
   * Stops retry mechanism for an operation
   */
  stopRetryMechanism(operationId) {
    const intervalId = this.retryIntervals.get(operationId);

    if (intervalId) {
      clearInterval(intervalId);
      this.retryIntervals.delete(operationId);
      console.log(`⏹️  Stopped retry mechanism for ${operationId}`);
    }

    // Check if all operations are synced
    if (this.operationQueue.length === 0 && this.retryIntervals.size === 0) {
      this.markAsSynced();
    }
  }

  /**
   * Marks all data as synced
   */
  markAsSynced() {
    this.syncStatus.isSynced = true;
    this.syncStatus.lastSyncTime = new Date();
    console.log(
      `\n🎉 DATABASE SYNC COMPLETE!\n` +
        `✓ All operations synced with MongoDB Atlas\n` +
        `✓ Switching to real-time updates only\n` +
        `✓ Last sync: ${this.syncStatus.lastSyncTime.toISOString()}\n`
    );
  }

  /**
   * Get sync status
   */
  getSyncStatus() {
    return {
      ...this.syncStatus,
      pendingOperations: this.operationQueue.length,
      activeRetries: this.retryIntervals.size,
    };
  }

  /**
   * Get pending operations
   */
  getPendingOperations() {
    return this.operationQueue.map((item) => ({
      id: item.id,
      operation: item.operation.type,
      collection: item.operation.collection,
      retryCount: item.retryCount,
      addedAt: item.addedAt,
    }));
  }

  /**
   * Clear all retries (use with caution)
   */
  clearAllRetries() {
    this.retryIntervals.forEach((intervalId) => clearInterval(intervalId));
    this.retryIntervals.clear();
    this.internetCheckIntervals.forEach((intervalId) =>
      clearInterval(intervalId)
    );
    this.internetCheckIntervals.clear();
    this.operationQueue = [];
    console.log("🧹 Cleared all retry intervals and operation queue");
  }
}

module.exports = new SyncService();
