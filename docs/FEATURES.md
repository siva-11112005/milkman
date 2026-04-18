# Dairy App Features (Separate Sections)

## 1. Authentication and Role Access
- Secure login for Admin and Staff users
- Role-based screen access
- Staff restrictions on financial visibility and admin-only screens

## 2. Profile Section
- View profile details (username, role, full name)
- Update full name
- Change password with current-password validation
- Profile updates logged in audit trail

## 3. Customer Management
- Add customer with name, phone, address, and category
- Customer categories:
  - Individual
  - Milk Industry
  - Tea Shop
- Search customers by name or phone
- View full customer list

## 4. Milk Entry Management
- Add collection entries for MORNING or EVENING sessions
- Record date, quantity, price per liter, and auto-computed amount
- One record per customer + date + session (duplicate prevention)

## 5. Anti-Fraud Record Lock
- Edit/delete allowed only within 1 hour of record creation
- Locked records show non-editable state in history UI
- Repository-level lock validation prevents bypass

## 6. Dashboard and Insights
- Daily milk total and earnings
- Monthly milk total and earnings
- Customer-type breakdown (Individual / Industry / Tea Shop)
- Quick access navigation to all major modules

## 7. Daily Session Summary
- Separate morning and evening totals for selected date
- Shows quantity, earnings, and number of entries by session
- Displays daily combined totals

## 8. Staff Management (Admin)
- Admin can create staff accounts
- Staff account listing with status

## 9. Monthly Reporting
- Customer-wise monthly summary
- Total quantity and amount aggregation
- Staff view can hide financial totals (role aware)

## 10. PDF Export
- Export monthly report to PDF file
- Includes customer-wise and total summary

## 11. Audit Log (Admin)
- View latest audit records
- Tracks action type, table/record target, actor, and timestamp
- Captures create/update/delete operations

## 12. Offline-First Data Layer
- Room database for local persistence
- LiveData updates for responsive UI
- MVVM architecture for maintainable modules

## 13. Atlas Backend Support
- Node.js backend integrated with MongoDB Atlas
- REST endpoints for auth, customers, milk, reports, and staff
- JWT-based API authentication
