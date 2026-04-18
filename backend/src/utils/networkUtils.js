const dns = require("dns").promises;
const net = require("net");

/**
 * Check if internet is available
 */
async function isInternetAvailable() {
  try {
    // Try to resolve a reliable DNS
    await dns.lookup("google.com");
    return true;
  } catch {
    try {
      // Fallback: Try MongoDB Atlas
      await dns.lookup("mongodb.com");
      return true;
    } catch {
      try {
        // Last resort: Try Cloudflare DNS
        await dns.resolve4("1.1.1.1");
        return true;
      } catch {
        return false;
      }
    }
  }
}

/**
 * Wait for internet to be available (checks every 10 seconds)
 */
async function waitForInternet() {
  let attempts = 0;
  const maxAttempts = 60; // 10 minutes max wait

  console.log("⏳ Waiting for internet connection...");

  while (attempts < maxAttempts) {
    attempts++;
    const available = await isInternetAvailable();

    if (available) {
      console.log("✅ Internet connection restored!");
      return true;
    }

    console.log(
      `⚠️  No internet (attempt ${attempts}/${maxAttempts})... retrying in 10s`
    );
    await new Promise((resolve) => setTimeout(resolve, 10000)); // Wait 10 seconds
  }

  console.error("❌ Internet connection not restored after 10 minutes");
  return false;
}

/**
 * Check MongoDB Atlas connectivity
 */
async function checkMongoDBConnectivity() {
  try {
    const mongoose = require("mongoose");
    if (mongoose.connection.readyState === 1) {
      console.log("✅ MongoDB connection is active");
      return true;
    }
    console.warn("⚠️  MongoDB connection not active, attempting reconnect...");
    return false;
  } catch (err) {
    console.error("❌ MongoDB connectivity check failed:", err.message);
    return false;
  }
}

module.exports = {
  isInternetAvailable,
  waitForInternet,
  checkMongoDBConnectivity,
};
