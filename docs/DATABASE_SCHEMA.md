# Database Schema (Room / SQLite)

The app uses Room with SQLite under the hood. Schema entities are defined in code and represented below.

## 1) users

```sql
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    username TEXT NOT NULL UNIQUE,
    passwordHash TEXT NOT NULL,
    fullName TEXT NOT NULL,
    role TEXT NOT NULL,            -- ADMIN / STAFF
    isActive INTEGER NOT NULL,     -- 1 = active, 0 = inactive
    createdAt INTEGER NOT NULL,
    createdBy INTEGER
);
```

## 2) customers

```sql
CREATE TABLE customers (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name TEXT NOT NULL,
    phone TEXT NOT NULL,
    address TEXT NOT NULL,
    type TEXT NOT NULL,            -- Individual / Milk Industry / Tea Shop
    createdAt INTEGER NOT NULL,
    createdBy INTEGER NOT NULL,
    updatedAt INTEGER,
    updatedBy INTEGER
);

CREATE INDEX index_customers_name ON customers(name);
CREATE INDEX index_customers_phone ON customers(phone);
```

## 3) milk_entries

```sql
CREATE TABLE milk_entries (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    customerId INTEGER NOT NULL,
    entryDate TEXT NOT NULL,       -- yyyy-MM-dd
    session TEXT NOT NULL,         -- MORNING / EVENING
    quantityLiters REAL NOT NULL,
    pricePerLiter REAL NOT NULL,
    amount REAL NOT NULL,
    createdAt INTEGER NOT NULL,
    createdBy INTEGER NOT NULL,
    updatedAt INTEGER,
    updatedBy INTEGER,
    FOREIGN KEY(customerId) REFERENCES customers(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE INDEX index_milk_entries_customerId ON milk_entries(customerId);
CREATE INDEX index_milk_entries_entryDate ON milk_entries(entryDate);
CREATE UNIQUE INDEX index_milk_entries_customer_date_session
    ON milk_entries(customerId, entryDate, session);
```

## 4) audit_logs

```sql
CREATE TABLE audit_logs (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    tableName TEXT NOT NULL,
    recordId INTEGER NOT NULL,
    action TEXT NOT NULL,          -- CREATE / UPDATE / DELETE
    userId INTEGER NOT NULL,
    details TEXT NOT NULL,
    createdAt INTEGER NOT NULL
);

CREATE INDEX index_audit_logs_table_record ON audit_logs(tableName, recordId);
CREATE INDEX index_audit_logs_createdAt ON audit_logs(createdAt);
```

## Fraud Lock Rule
- A milk entry can be edited/deleted only when:
  - `currentTimeMillis - createdAt <= 3600000`
- After 1 hour, record becomes permanently locked in UI and repository checks.
