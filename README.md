# Milkman Dairy Management Android App

Offline-first Android application for milk collection management with role-based access, anti-fraud record locking, monthly billing, and PDF report export.

## Tech Stack
- Kotlin
- MVVM (ViewModel + LiveData)
- Room Database
- Material Design UI
- RecyclerView lists

## Roles
- Admin (milkman)
- Staff (created by admin)

## Core Features
- Customer management (Individual / Milk Industry / Tea Shop)
- Milk entries for Morning and Evening sessions
- Auto amount calculation (`quantity * price`)
- Fraud prevention: edit/delete only within 1 hour of entry
- Dashboard with daily/monthly totals and customer type breakdown
- Daily session summary (morning vs evening totals)
- Monthly customer-wise reporting
- PDF export for monthly reports
- Audit logs for create/update/delete actions
- Profile section (view/update name, change password)

## Default Admin Credentials
- Username: admin
- Password: admin123

Change this immediately in production use.

## Project Structure
- `app/src/main/java/com/milkman/dairyapp/data` -> Room entities, DAOs, repositories
- `app/src/main/java/com/milkman/dairyapp/viewmodel` -> MVVM viewmodels
- `app/src/main/java/com/milkman/dairyapp/ui` -> Android screens and adapters
- `app/src/main/java/com/milkman/dairyapp/util` -> helpers (session, time, PDF, hashing)
- `docs` -> database and build instructions

## Build APK
See `docs/APK_BUILD_INSTRUCTIONS.md`.

## Feature List by Sections
See `docs/FEATURES.md` for full feature documentation with separate sections.
