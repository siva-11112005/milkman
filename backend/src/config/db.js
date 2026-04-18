const mongoose = require("mongoose");
const { mongoUri } = require("./env");

const connectionOptions = {
  // Connection Pool Settings
  maxPoolSize: 20,
  minPoolSize: 5,
  maxIdleTimeMS: 45000,

  // Timeout Settings
  serverSelectionTimeoutMS: 10000,
  socketTimeoutMS: 45000,
  connectTimeoutMS: 10000,
  family: 4,

  // Reliability Settings
  retryWrites: true,
  w: "majority",
  journal: true,

  // Connection String Parser
  authSource: "admin",
};

let listenersAttached = false;
let indexCleanupDone = false;
let connectingPromise = null;

function attachConnectionListeners() {
  if (listenersAttached) {
    return;
  }

  mongoose.connection.on("connected", () => {
    console.log("📡 Mongoose connected to MongoDB");
  });

  mongoose.connection.on("error", (err) => {
    console.error("❌ Mongoose connection error:", err.message);
  });

  mongoose.connection.on("disconnected", () => {
    console.warn("⚠️  Mongoose disconnected from MongoDB");
  });

  listenersAttached = true;
}

async function cleanupUserIndexes() {
  if (indexCleanupDone) {
    return;
  }

  try {
    const db = mongoose.connection.db;
    if (!db) {
      return;
    }

    const collection = db.collection("users");
    const indexInfo = await collection.listIndexes().toArray();
    console.log(
      "📋 Current indexes on users collection:",
      indexInfo.map((idx) => idx.name)
    );

    for (const index of indexInfo) {
      if (index.name !== "_id_") {
        try {
          await collection.dropIndex(index.name);
          console.log(`✓ Dropped index: ${index.name}`);
        } catch (err) {
          console.log(`⚠️  Could not drop ${index.name}:`, err.message);
        }
      }
    }

    indexCleanupDone = true;
  } catch (err) {
    console.log("⚠️  Index cleanup skipped:", err.message);
  }
}

async function connectDb() {
  if (mongoose.connection.readyState === 1) {
    return mongoose.connection;
  }

  if (connectingPromise) {
    return connectingPromise;
  }

  connectingPromise = (async () => {
    try {
      console.log("🔗 Connecting to MongoDB Atlas...");
      console.log("📍 Database: milk");
      console.log("🌍 Cluster: Cluster0");

      const connection = await mongoose.connect(mongoUri, connectionOptions);

      console.log("✅ MongoDB connected successfully to milk database");

      attachConnectionListeners();

      // Wait for connection to stabilize
      await new Promise((resolve) => setTimeout(resolve, 1000));

      const dbName = mongoose.connection.name || "milk";
      console.log(`✓ Connected to database: ${dbName}`);

      await cleanupUserIndexes();

      return connection;
    } catch (err) {
      console.error("❌ Failed to connect to MongoDB:", err.message);
      console.error("💡 Please check:");
      console.error("   1. MongoDB URI is correct");
      console.error("   2. IP address is whitelisted in MongoDB Atlas");
      console.error("   3. Database user credentials are correct");
      console.error("   4. Network connection is stable");
      throw err;
    } finally {
      connectingPromise = null;
    }
  })();

  return connectingPromise;
}

async function ensureDbConnection() {
  if (mongoose.connection.readyState === 1) {
    return mongoose.connection;
  }

  try {
    return await connectDb();
  } catch (err) {
    const wrappedError = new Error(
      "Database connection unavailable. Unable to reach MongoDB Atlas"
    );
    wrappedError.status = 503;
    wrappedError.code = "DB_CONNECTION_FAILED";
    wrappedError.cause = err;
    throw wrappedError;
  }
}

module.exports = { connectDb, ensureDbConnection };
