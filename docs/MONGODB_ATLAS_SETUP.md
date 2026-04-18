# MongoDB Atlas Setup and Run Guide

## 1. Create Atlas connection
- Create a MongoDB Atlas cluster.
- Create a database user (username/password).
- In Network Access, allow your current IP address.
- Copy Atlas connection string (SRV format).

Example:
mongodb+srv://username:password@cluster-name.xxxxx.mongodb.net/milkman_dairy?retryWrites=true&w=majority

## 2. Configure backend env
Edit file: backend/.env

Required fields:
- MONGODB_URI: your real Atlas URI
- JWT_SECRET: long random secret
- PORT: 8080 (or any free port)

## 3. Install backend dependencies
From project root:
- cd backend
- npm install

## 4. Start backend
- node src/server.js

Expected success logs:
- Default admin created (first run only)
- Backend listening on http://localhost:8080

## 5. Test API quickly
- GET http://localhost:8080/health

## 6. Main endpoints
- POST /api/auth/login
- GET, POST /api/customers
- GET, POST /api/milk
- PUT, DELETE /api/milk/:id
- GET /api/reports/dashboard
- GET /api/reports/monthly
- GET, POST /api/staff

## 7. Important note about Android app
Current Android app is still using Room for offline local storage.
Atlas is now added through a secure backend API in backend/.
To make Android use Atlas live data, next step is adding Retrofit API integration and replacing or syncing Room repositories.
