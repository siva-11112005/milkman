const bcrypt = require("bcryptjs");
const User = require("../models/User");
const { defaultAdmin } = require("../config/env");

async function seedDefaultAdmin() {
  try {
    console.log("🌱 Seeding default admin...");
    
    const existing = await User.findOne({ username: defaultAdmin.username });
    if (existing) {
      console.log("✓ Default admin already exists");
      return;
    }

    const passwordHash = await bcrypt.hash(defaultAdmin.password, 10);
    const newAdmin = await User.create({
      username: defaultAdmin.username,
      passwordHash,
      fullName: defaultAdmin.fullName,
      role: "ADMIN",
      isActive: true,
      createdBy: null,
    });

    console.log("✅ Default admin created successfully:", newAdmin.username);
  } catch (err) {
    console.error("❌ Error creating default admin:", err.message);
    if (err.code === 11000) {
      console.log("ℹ Duplicate key error - admin may already exist");
      // Try to find and update if exists
      try {
        const existing = await User.findOne({ username: defaultAdmin.username });
        if (existing) {
          existing.isActive = true;
          await existing.save();
          console.log("✓ Reactivated existing admin");
        }
      } catch (findErr) {
        console.error("Could not recover from duplicate:", findErr.message);
      }
    } else {
      throw err;
    }
  }
}

async function seedSuperUser() {
  try {
    console.log("🌱 Seeding super user...");
    
    const existing = await User.findOne({ username: "7418042205" });
    if (existing) {
      console.log("✓ Super user already exists");
      return;
    }

    const passwordHash = await bcrypt.hash("siva3107", 10);
    const newUser = await User.create({
      username: "7418042205",
      passwordHash,
      fullName: "Super Admin",
      role: "SUPER_USER",
      isActive: true,
      createdBy: null,
    });

    console.log("✅ Super user created successfully:", newUser._id);
  } catch (err) {
    console.error("❌ Error creating super user:", err.message);
    if (err.code === 11000) {
      console.log("ℹ Duplicate key detected - attempting recovery...");
      try {
        await User.deleteMany({ username: "7418042205" });
        const passwordHash = await bcrypt.hash("siva3107", 10);
        const newUser = await User.create({
          username: "7418042205",
          passwordHash,
          fullName: "Super Admin",
          role: "SUPER_USER",
          isActive: true,
          createdBy: null,
        });
        console.log("✅ Super user recreated successfully:", newUser._id);
      } catch (recErr) {
        console.error("❌ Failed to recreate super user:", recErr.message);
        throw recErr;
      }
    } else {
      throw err;
    }
  }
}

module.exports = { seedDefaultAdmin, seedSuperUser };
