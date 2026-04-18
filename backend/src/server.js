const express = require("express");
const cors = require("cors");
const morgan = require("morgan");
const { port } = require("./config/env");
const { connectDb } = require("./config/db");
const { seedDefaultAdmin, seedSuperUser } = require("./services/seedService");
const { errorHandler } = require("./middleware/errorHandler");
const { requireAuth } = require("./middleware/auth");
const syncService = require("./services/syncService");

const authRoutes = require("./routes/authRoutes");
const customerRoutes = require("./routes/customerRoutes");
const milkRoutes = require("./routes/milkRoutes");
const reportRoutes = require("./routes/reportRoutes");
const staffRoutes = require("./routes/staffRoutes");

async function start() {
  try {
    console.log(
      "\n╔════════════════════════════════════════════════════╗"
    );
    console.log(
      "║   🥛  MILKMAN BACKEND - STARTING                   ║"
    );
    console.log(
      "╚════════════════════════════════════════════════════╝\n"
    );

    await connectDb();
    console.log("\n📊 Database connected, seeding users...");

    await seedDefaultAdmin();
    await seedSuperUser();

    console.log("✅ User seeding complete\n");

    const app = express();
    app.use(cors());
    app.use(express.json());
    app.use(morgan("dev"));

    // Health check endpoint
    app.get("/health", (_req, res) => {
      res.json({
        ok: true,
        service: "milkman-backend",
        timestamp: new Date().toISOString(),
      });
    });

    // Sync status endpoint
    app.get("/api/sync/status", (_req, res) => {
      const status = syncService.getSyncStatus();
      res.json({
        status,
        isSynced: status.isSynced,
        message: status.isSynced
          ? "✅ All data synced with MongoDB Atlas"
          : `⏳ Syncing... ${status.pendingOperations} pending operations`,
      });
    });

    // Pending operations endpoint
    app.get("/api/sync/pending", (_req, res) => {
      const pending = syncService.getPendingOperations();
      res.json({ pending, count: pending.length });
    });

    // Diagnostic endpoint to test database operations
    app.post("/api/test/create-customer", requireAuth, async (_req, res, next) => {
      try {
        console.log("\n🧪 DIAGNOSTIC: Testing Customer.create()...");
        
        const Customer = require("./models/Customer");
        const testCustomer = await Customer.create({
          name: "Test Customer Diagnostic",
          phone: "9999999999",
          address: "Test Address",
          type: "Individual",
          createdBy: _req.user.sub,
          updatedBy: null,
        });
        
        console.log("✅ DIAGNOSTIC: Customer created successfully!", testCustomer._id);
        res.json({
          success: true,
          message: "Customer created successfully",
          data: testCustomer,
        });
      } catch (error) {
        console.error("❌ DIAGNOSTIC: Failed to create customer");
        console.error("   Error:", error.message);
        console.error("   Code:", error.code);
        console.error("   Stack:", error.stack);
        res.json({
          success: false,
          message: "Failed to create customer",
          error: error.message,
          code: error.code,
          stack: error.stack,
        });
      }
    });

    // Routes
    app.use("/api/auth", authRoutes);
    app.use("/api/customers", customerRoutes);
    app.use("/api/milk", milkRoutes);
    app.use("/api/reports", reportRoutes);
    app.use("/api/staff", staffRoutes);

    // Error handler
    app.use(errorHandler);

    app.listen(port, () => {
      console.log(`\n✅ Backend listening on http://localhost:${port}`);
      console.log(
        `🔍 Check sync status: http://localhost:${port}/api/sync/status`
      );
      console.log(`📝 Check pending ops: http://localhost:${port}/api/sync/pending\n`);
    });
  } catch (err) {
    console.error("\n❌ Failed to start backend:", err.message);
    console.error(err.stack);
    process.exit(1);
  }
}

start();
