const dotenv = require("dotenv");

dotenv.config();

const required = ["MONGODB_URI", "JWT_SECRET"];
for (const key of required) {
  if (!process.env[key]) {
    throw new Error(`Missing required env var: ${key}`);
  }
}

module.exports = {
  port: Number(process.env.PORT || 8080),
  mongoUri: process.env.MONGODB_URI,
  jwtSecret: process.env.JWT_SECRET,
  syncRetryInterval: Number(process.env.DB_SYNC_RETRY_INTERVAL || 60000), // 1 minute default
  maxRetryAttempts: Number(process.env.DB_MAX_RETRY_ATTEMPTS || 5),
  nodeEnv: process.env.NODE_ENV || "development",
  defaultAdmin: {
    username: process.env.DEFAULT_ADMIN_USERNAME || "admin",
    password: process.env.DEFAULT_ADMIN_PASSWORD || "admin123",
    fullName: process.env.DEFAULT_ADMIN_NAME || "Milkman Admin",
  },
};
