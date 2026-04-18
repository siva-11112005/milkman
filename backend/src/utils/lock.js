const LOCK_WINDOW_MS = 60 * 60 * 1000;

function isLocked(createdAt) {
  return Date.now() - new Date(createdAt).getTime() > LOCK_WINDOW_MS;
}

module.exports = { isLocked, LOCK_WINDOW_MS };
