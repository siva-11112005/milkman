const jwt = require("jsonwebtoken");
const { jwtSecret } = require("../config/env");
const User = require("../models/User");

async function requireAuth(req, res, next) {
  const header = req.headers.authorization || "";
  const token = header.startsWith("Bearer ") ? header.slice(7) : null;
  if (!token) {
    return res.status(401).json({ message: "Missing auth token" });
  }

  try {
    const payload = jwt.verify(token, jwtSecret);
    req.user = payload;

    // Optional scope override for super users to act as a specific admin.
    const requestedAdminScope = (req.headers["x-admin-scope-id"] || "").toString().trim();
    if (payload.role === "SUPER_USER" && requestedAdminScope) {
      const scopedAdmin = await User.findOne({ _id: requestedAdminScope, role: "ADMIN", isActive: true })
        .select("_id username fullName role")
        .lean();

      if (scopedAdmin) {
        req.effectiveUser = {
          sub: String(scopedAdmin._id),
          username: scopedAdmin.username,
          fullName: scopedAdmin.fullName,
          role: "ADMIN",
          actingAsAdmin: true,
          originalRole: payload.role,
          originalSub: payload.sub,
        };
      }
    }

    if (!req.effectiveUser) {
      req.effectiveUser = payload;
    }

    return next();
  } catch (_err) {
    return res.status(401).json({ message: "Invalid token" });
  }
}

function requireRole(...roles) {
  return (req, res, next) => {
    const currentUser = req.effectiveUser || req.user;
    if (!currentUser || !roles.includes(currentUser.role)) {
      return res.status(403).json({ message: "Access denied" });
    }
    return next();
  };
}

module.exports = { requireAuth, requireRole };
