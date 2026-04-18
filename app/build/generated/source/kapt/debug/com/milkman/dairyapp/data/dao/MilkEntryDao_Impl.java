package com.milkman.dairyapp.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.milkman.dairyapp.data.entity.MilkEntryEntity;
import com.milkman.dairyapp.data.model.DailySessionBreakdown;
import com.milkman.dairyapp.data.model.MilkEntryWithCustomer;
import com.milkman.dairyapp.data.model.MonthlyCustomerReport;
import com.milkman.dairyapp.data.model.TypeBreakdown;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MilkEntryDao_Impl implements MilkEntryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MilkEntryEntity> __insertionAdapterOfMilkEntryEntity;

  private final EntityDeletionOrUpdateAdapter<MilkEntryEntity> __deletionAdapterOfMilkEntryEntity;

  private final EntityDeletionOrUpdateAdapter<MilkEntryEntity> __updateAdapterOfMilkEntryEntity;

  public MilkEntryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMilkEntryEntity = new EntityInsertionAdapter<MilkEntryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `milk_entries` (`id`,`customerId`,`entryDate`,`session`,`entryType`,`quantityLiters`,`pricePerLiter`,`amount`,`createdAt`,`createdBy`,`updatedAt`,`updatedBy`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MilkEntryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getCustomerId());
        if (entity.getEntryDate() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEntryDate());
        }
        if (entity.getSession() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSession());
        }
        if (entity.getEntryType() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getEntryType());
        }
        statement.bindDouble(6, entity.getQuantityLiters());
        statement.bindDouble(7, entity.getPricePerLiter());
        statement.bindDouble(8, entity.getAmount());
        statement.bindLong(9, entity.getCreatedAt());
        statement.bindLong(10, entity.getCreatedBy());
        if (entity.getUpdatedAt() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getUpdatedAt());
        }
        if (entity.getUpdatedBy() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getUpdatedBy());
        }
      }
    };
    this.__deletionAdapterOfMilkEntryEntity = new EntityDeletionOrUpdateAdapter<MilkEntryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `milk_entries` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MilkEntryEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfMilkEntryEntity = new EntityDeletionOrUpdateAdapter<MilkEntryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `milk_entries` SET `id` = ?,`customerId` = ?,`entryDate` = ?,`session` = ?,`entryType` = ?,`quantityLiters` = ?,`pricePerLiter` = ?,`amount` = ?,`createdAt` = ?,`createdBy` = ?,`updatedAt` = ?,`updatedBy` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MilkEntryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getCustomerId());
        if (entity.getEntryDate() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEntryDate());
        }
        if (entity.getSession() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSession());
        }
        if (entity.getEntryType() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getEntryType());
        }
        statement.bindDouble(6, entity.getQuantityLiters());
        statement.bindDouble(7, entity.getPricePerLiter());
        statement.bindDouble(8, entity.getAmount());
        statement.bindLong(9, entity.getCreatedAt());
        statement.bindLong(10, entity.getCreatedBy());
        if (entity.getUpdatedAt() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getUpdatedAt());
        }
        if (entity.getUpdatedBy() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getUpdatedBy());
        }
        statement.bindLong(13, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final MilkEntryEntity entry, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfMilkEntryEntity.insertAndReturnId(entry);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object delete(final MilkEntryEntity entry, final Continuation<? super Integer> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __deletionAdapterOfMilkEntryEntity.handle(entry);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object update(final MilkEntryEntity entry, final Continuation<? super Integer> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __updateAdapterOfMilkEntryEntity.handle(entry);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object findById(final int id, final Continuation<? super MilkEntryEntity> arg1) {
    final String _sql = "SELECT * FROM milk_entries WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MilkEntryEntity>() {
      @Override
      @Nullable
      public MilkEntryEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCustomerId = CursorUtil.getColumnIndexOrThrow(_cursor, "customerId");
          final int _cursorIndexOfEntryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "entryDate");
          final int _cursorIndexOfSession = CursorUtil.getColumnIndexOrThrow(_cursor, "session");
          final int _cursorIndexOfEntryType = CursorUtil.getColumnIndexOrThrow(_cursor, "entryType");
          final int _cursorIndexOfQuantityLiters = CursorUtil.getColumnIndexOrThrow(_cursor, "quantityLiters");
          final int _cursorIndexOfPricePerLiter = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerLiter");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "createdBy");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfUpdatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedBy");
          final MilkEntryEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpCustomerId;
            _tmpCustomerId = _cursor.getInt(_cursorIndexOfCustomerId);
            final String _tmpEntryDate;
            if (_cursor.isNull(_cursorIndexOfEntryDate)) {
              _tmpEntryDate = null;
            } else {
              _tmpEntryDate = _cursor.getString(_cursorIndexOfEntryDate);
            }
            final String _tmpSession;
            if (_cursor.isNull(_cursorIndexOfSession)) {
              _tmpSession = null;
            } else {
              _tmpSession = _cursor.getString(_cursorIndexOfSession);
            }
            final String _tmpEntryType;
            if (_cursor.isNull(_cursorIndexOfEntryType)) {
              _tmpEntryType = null;
            } else {
              _tmpEntryType = _cursor.getString(_cursorIndexOfEntryType);
            }
            final double _tmpQuantityLiters;
            _tmpQuantityLiters = _cursor.getDouble(_cursorIndexOfQuantityLiters);
            final double _tmpPricePerLiter;
            _tmpPricePerLiter = _cursor.getDouble(_cursorIndexOfPricePerLiter);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final int _tmpCreatedBy;
            _tmpCreatedBy = _cursor.getInt(_cursorIndexOfCreatedBy);
            final Long _tmpUpdatedAt;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmpUpdatedAt = null;
            } else {
              _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Integer _tmpUpdatedBy;
            if (_cursor.isNull(_cursorIndexOfUpdatedBy)) {
              _tmpUpdatedBy = null;
            } else {
              _tmpUpdatedBy = _cursor.getInt(_cursorIndexOfUpdatedBy);
            }
            _result = new MilkEntryEntity(_tmpId,_tmpCustomerId,_tmpEntryDate,_tmpSession,_tmpEntryType,_tmpQuantityLiters,_tmpPricePerLiter,_tmpAmount,_tmpCreatedAt,_tmpCreatedBy,_tmpUpdatedAt,_tmpUpdatedBy);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public LiveData<List<MilkEntryWithCustomer>> getEntries(final String date,
      final Integer customerId, final String customerCategory, final String customerType,
      final String entryType) {
    final String _sql = "\n"
            + "        SELECT\n"
            + "            m.id,\n"
            + "            m.customerId,\n"
            + "            c.name AS customerName,\n"
            + "            c.category AS customerCategory,\n"
            + "            c.type AS customerType,\n"
            + "            m.entryDate,\n"
            + "            m.session,\n"
            + "            m.entryType,\n"
            + "            m.quantityLiters,\n"
            + "            m.pricePerLiter,\n"
            + "            m.amount,\n"
            + "            m.createdAt,\n"
            + "            m.createdBy,\n"
            + "            m.updatedAt,\n"
            + "            m.updatedBy\n"
            + "        FROM milk_entries m\n"
            + "        INNER JOIN customers c ON c.id = m.customerId\n"
            + "        WHERE (? IS NULL OR m.entryDate = ?)\n"
            + "          AND (? IS NULL OR m.customerId = ?)\n"
            + "          AND (? IS NULL OR c.category = ?)\n"
            + "          AND (? IS NULL OR c.type = ?)\n"
            + "          AND (? IS NULL OR m.entryType = ?)\n"
            + "        ORDER BY m.entryDate DESC,\n"
            + "                 CASE WHEN m.session = 'MORNING' THEN 0 ELSE 1 END ASC,\n"
            + "                 m.createdAt DESC\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 10);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 2;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 3;
    if (customerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, customerId);
    }
    _argIndex = 4;
    if (customerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, customerId);
    }
    _argIndex = 5;
    if (customerCategory == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, customerCategory);
    }
    _argIndex = 6;
    if (customerCategory == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, customerCategory);
    }
    _argIndex = 7;
    if (customerType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, customerType);
    }
    _argIndex = 8;
    if (customerType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, customerType);
    }
    _argIndex = 9;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 10;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"milk_entries",
        "customers"}, false, new Callable<List<MilkEntryWithCustomer>>() {
      @Override
      @Nullable
      public List<MilkEntryWithCustomer> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfCustomerId = 1;
          final int _cursorIndexOfCustomerName = 2;
          final int _cursorIndexOfCustomerCategory = 3;
          final int _cursorIndexOfCustomerType = 4;
          final int _cursorIndexOfEntryDate = 5;
          final int _cursorIndexOfSession = 6;
          final int _cursorIndexOfEntryType = 7;
          final int _cursorIndexOfQuantityLiters = 8;
          final int _cursorIndexOfPricePerLiter = 9;
          final int _cursorIndexOfAmount = 10;
          final int _cursorIndexOfCreatedAt = 11;
          final int _cursorIndexOfCreatedBy = 12;
          final int _cursorIndexOfUpdatedAt = 13;
          final int _cursorIndexOfUpdatedBy = 14;
          final List<MilkEntryWithCustomer> _result = new ArrayList<MilkEntryWithCustomer>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MilkEntryWithCustomer _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpCustomerId;
            _tmpCustomerId = _cursor.getInt(_cursorIndexOfCustomerId);
            final String _tmpCustomerName;
            if (_cursor.isNull(_cursorIndexOfCustomerName)) {
              _tmpCustomerName = null;
            } else {
              _tmpCustomerName = _cursor.getString(_cursorIndexOfCustomerName);
            }
            final String _tmpCustomerCategory;
            if (_cursor.isNull(_cursorIndexOfCustomerCategory)) {
              _tmpCustomerCategory = null;
            } else {
              _tmpCustomerCategory = _cursor.getString(_cursorIndexOfCustomerCategory);
            }
            final String _tmpCustomerType;
            if (_cursor.isNull(_cursorIndexOfCustomerType)) {
              _tmpCustomerType = null;
            } else {
              _tmpCustomerType = _cursor.getString(_cursorIndexOfCustomerType);
            }
            final String _tmpEntryDate;
            if (_cursor.isNull(_cursorIndexOfEntryDate)) {
              _tmpEntryDate = null;
            } else {
              _tmpEntryDate = _cursor.getString(_cursorIndexOfEntryDate);
            }
            final String _tmpSession;
            if (_cursor.isNull(_cursorIndexOfSession)) {
              _tmpSession = null;
            } else {
              _tmpSession = _cursor.getString(_cursorIndexOfSession);
            }
            final String _tmpEntryType;
            if (_cursor.isNull(_cursorIndexOfEntryType)) {
              _tmpEntryType = null;
            } else {
              _tmpEntryType = _cursor.getString(_cursorIndexOfEntryType);
            }
            final double _tmpQuantityLiters;
            _tmpQuantityLiters = _cursor.getDouble(_cursorIndexOfQuantityLiters);
            final double _tmpPricePerLiter;
            _tmpPricePerLiter = _cursor.getDouble(_cursorIndexOfPricePerLiter);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final int _tmpCreatedBy;
            _tmpCreatedBy = _cursor.getInt(_cursorIndexOfCreatedBy);
            final Long _tmpUpdatedAt;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmpUpdatedAt = null;
            } else {
              _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Integer _tmpUpdatedBy;
            if (_cursor.isNull(_cursorIndexOfUpdatedBy)) {
              _tmpUpdatedBy = null;
            } else {
              _tmpUpdatedBy = _cursor.getInt(_cursorIndexOfUpdatedBy);
            }
            _item = new MilkEntryWithCustomer(_tmpId,_tmpCustomerId,_tmpCustomerName,_tmpCustomerCategory,_tmpCustomerType,_tmpEntryDate,_tmpSession,_tmpEntryType,_tmpQuantityLiters,_tmpPricePerLiter,_tmpAmount,_tmpCreatedAt,_tmpCreatedBy,_tmpUpdatedAt,_tmpUpdatedBy);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<MilkEntryWithCustomer>> getMilkEntries() {
    final String _sql = "\n"
            + "        SELECT\n"
            + "            m.id,\n"
            + "            m.customerId,\n"
            + "            c.name AS customerName,\n"
            + "            c.category AS customerCategory,\n"
            + "            c.type AS customerType,\n"
            + "            m.entryDate,\n"
            + "            m.session,\n"
            + "            m.entryType,\n"
            + "            m.quantityLiters,\n"
            + "            m.pricePerLiter,\n"
            + "            m.amount,\n"
            + "            m.createdAt,\n"
            + "            m.createdBy,\n"
            + "            m.updatedAt,\n"
            + "            m.updatedBy\n"
            + "        FROM milk_entries m\n"
            + "        INNER JOIN customers c ON c.id = m.customerId\n"
            + "        ORDER BY m.entryDate DESC,\n"
            + "                 CASE WHEN m.session = 'MORNING' THEN 0 ELSE 1 END ASC,\n"
            + "                 m.createdAt DESC\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"milk_entries",
        "customers"}, false, new Callable<List<MilkEntryWithCustomer>>() {
      @Override
      @Nullable
      public List<MilkEntryWithCustomer> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfCustomerId = 1;
          final int _cursorIndexOfCustomerName = 2;
          final int _cursorIndexOfCustomerCategory = 3;
          final int _cursorIndexOfCustomerType = 4;
          final int _cursorIndexOfEntryDate = 5;
          final int _cursorIndexOfSession = 6;
          final int _cursorIndexOfEntryType = 7;
          final int _cursorIndexOfQuantityLiters = 8;
          final int _cursorIndexOfPricePerLiter = 9;
          final int _cursorIndexOfAmount = 10;
          final int _cursorIndexOfCreatedAt = 11;
          final int _cursorIndexOfCreatedBy = 12;
          final int _cursorIndexOfUpdatedAt = 13;
          final int _cursorIndexOfUpdatedBy = 14;
          final List<MilkEntryWithCustomer> _result = new ArrayList<MilkEntryWithCustomer>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MilkEntryWithCustomer _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpCustomerId;
            _tmpCustomerId = _cursor.getInt(_cursorIndexOfCustomerId);
            final String _tmpCustomerName;
            if (_cursor.isNull(_cursorIndexOfCustomerName)) {
              _tmpCustomerName = null;
            } else {
              _tmpCustomerName = _cursor.getString(_cursorIndexOfCustomerName);
            }
            final String _tmpCustomerCategory;
            if (_cursor.isNull(_cursorIndexOfCustomerCategory)) {
              _tmpCustomerCategory = null;
            } else {
              _tmpCustomerCategory = _cursor.getString(_cursorIndexOfCustomerCategory);
            }
            final String _tmpCustomerType;
            if (_cursor.isNull(_cursorIndexOfCustomerType)) {
              _tmpCustomerType = null;
            } else {
              _tmpCustomerType = _cursor.getString(_cursorIndexOfCustomerType);
            }
            final String _tmpEntryDate;
            if (_cursor.isNull(_cursorIndexOfEntryDate)) {
              _tmpEntryDate = null;
            } else {
              _tmpEntryDate = _cursor.getString(_cursorIndexOfEntryDate);
            }
            final String _tmpSession;
            if (_cursor.isNull(_cursorIndexOfSession)) {
              _tmpSession = null;
            } else {
              _tmpSession = _cursor.getString(_cursorIndexOfSession);
            }
            final String _tmpEntryType;
            if (_cursor.isNull(_cursorIndexOfEntryType)) {
              _tmpEntryType = null;
            } else {
              _tmpEntryType = _cursor.getString(_cursorIndexOfEntryType);
            }
            final double _tmpQuantityLiters;
            _tmpQuantityLiters = _cursor.getDouble(_cursorIndexOfQuantityLiters);
            final double _tmpPricePerLiter;
            _tmpPricePerLiter = _cursor.getDouble(_cursorIndexOfPricePerLiter);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final int _tmpCreatedBy;
            _tmpCreatedBy = _cursor.getInt(_cursorIndexOfCreatedBy);
            final Long _tmpUpdatedAt;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmpUpdatedAt = null;
            } else {
              _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Integer _tmpUpdatedBy;
            if (_cursor.isNull(_cursorIndexOfUpdatedBy)) {
              _tmpUpdatedBy = null;
            } else {
              _tmpUpdatedBy = _cursor.getInt(_cursorIndexOfUpdatedBy);
            }
            _item = new MilkEntryWithCustomer(_tmpId,_tmpCustomerId,_tmpCustomerName,_tmpCustomerCategory,_tmpCustomerType,_tmpEntryDate,_tmpSession,_tmpEntryType,_tmpQuantityLiters,_tmpPricePerLiter,_tmpAmount,_tmpCreatedAt,_tmpCreatedBy,_tmpUpdatedAt,_tmpUpdatedBy);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Double> getDailyQuantity(final String date, final String entryType,
      final Integer customerId) {
    final String _sql = "\n"
            + "        SELECT IFNULL(SUM(quantityLiters), 0)\n"
            + "        FROM milk_entries\n"
            + "        WHERE entryDate = ?\n"
            + "          AND (? IS NULL OR entryType = ?)\n"
            + "          AND (? IS NULL OR customerId = ?)\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 2;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 3;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 4;
    if (customerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, customerId);
    }
    _argIndex = 5;
    if (customerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, customerId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"milk_entries"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Double> getDailyAmount(final String date, final String entryType,
      final Integer customerId) {
    final String _sql = "\n"
            + "        SELECT IFNULL(SUM(amount), 0)\n"
            + "        FROM milk_entries\n"
            + "        WHERE entryDate = ?\n"
            + "          AND (? IS NULL OR entryType = ?)\n"
            + "          AND (? IS NULL OR customerId = ?)\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 2;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 3;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 4;
    if (customerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, customerId);
    }
    _argIndex = 5;
    if (customerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, customerId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"milk_entries"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Double> getMonthlyQuantity(final String month, final String entryType,
      final Integer customerId) {
    final String _sql = "\n"
            + "        SELECT IFNULL(SUM(quantityLiters), 0)\n"
            + "        FROM milk_entries\n"
            + "        WHERE substr(entryDate, 1, 7) = ?\n"
            + "          AND (? IS NULL OR entryType = ?)\n"
            + "          AND (? IS NULL OR customerId = ?)\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (month == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, month);
    }
    _argIndex = 2;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 3;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 4;
    if (customerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, customerId);
    }
    _argIndex = 5;
    if (customerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, customerId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"milk_entries"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Double> getMonthlyAmount(final String month, final String entryType,
      final Integer customerId) {
    final String _sql = "\n"
            + "        SELECT IFNULL(SUM(amount), 0)\n"
            + "        FROM milk_entries\n"
            + "        WHERE substr(entryDate, 1, 7) = ?\n"
            + "          AND (? IS NULL OR entryType = ?)\n"
            + "          AND (? IS NULL OR customerId = ?)\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (month == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, month);
    }
    _argIndex = 2;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 3;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 4;
    if (customerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, customerId);
    }
    _argIndex = 5;
    if (customerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, customerId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"milk_entries"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<TypeBreakdown>> getTypeBreakdown(final String month,
      final String customerCategory, final String entryType) {
    final String _sql = "\n"
            + "        SELECT\n"
            + "            c.type AS customerType,\n"
            + "            IFNULL(SUM(m.quantityLiters), 0) AS totalQuantity,\n"
            + "            IFNULL(SUM(m.amount), 0) AS totalAmount\n"
            + "        FROM milk_entries m\n"
            + "        INNER JOIN customers c ON c.id = m.customerId\n"
            + "        WHERE substr(m.entryDate, 1, 7) = ?\n"
            + "          AND (? IS NULL OR c.category = ?)\n"
            + "          AND (? IS NULL OR m.entryType = ?)\n"
            + "        GROUP BY c.type\n"
            + "        ORDER BY c.type ASC\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (month == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, month);
    }
    _argIndex = 2;
    if (customerCategory == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, customerCategory);
    }
    _argIndex = 3;
    if (customerCategory == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, customerCategory);
    }
    _argIndex = 4;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 5;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"milk_entries",
        "customers"}, false, new Callable<List<TypeBreakdown>>() {
      @Override
      @Nullable
      public List<TypeBreakdown> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCustomerType = 0;
          final int _cursorIndexOfTotalQuantity = 1;
          final int _cursorIndexOfTotalAmount = 2;
          final List<TypeBreakdown> _result = new ArrayList<TypeBreakdown>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TypeBreakdown _item;
            final String _tmpCustomerType;
            if (_cursor.isNull(_cursorIndexOfCustomerType)) {
              _tmpCustomerType = null;
            } else {
              _tmpCustomerType = _cursor.getString(_cursorIndexOfCustomerType);
            }
            final double _tmpTotalQuantity;
            _tmpTotalQuantity = _cursor.getDouble(_cursorIndexOfTotalQuantity);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            _item = new TypeBreakdown(_tmpCustomerType,_tmpTotalQuantity,_tmpTotalAmount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<MonthlyCustomerReport>> getMonthlyCustomerReport(final String month,
      final String customerCategory, final String entryType) {
    final String _sql = "\n"
            + "        SELECT\n"
            + "            c.id AS customerId,\n"
            + "            c.name AS customerName,\n"
            + "            c.type AS customerType,\n"
            + "            IFNULL(SUM(m.quantityLiters), 0) AS totalQuantity,\n"
            + "            IFNULL(SUM(m.amount), 0) AS totalAmount\n"
            + "        FROM customers c\n"
            + "        LEFT JOIN milk_entries m\n"
            + "            ON m.customerId = c.id\n"
            + "           AND substr(m.entryDate, 1, 7) = ?\n"
            + "           AND (? IS NULL OR m.entryType = ?)\n"
            + "        WHERE (? IS NULL OR c.category = ?)\n"
            + "        GROUP BY c.id, c.name, c.type\n"
            + "        ORDER BY c.name ASC\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (month == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, month);
    }
    _argIndex = 2;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 3;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 4;
    if (customerCategory == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, customerCategory);
    }
    _argIndex = 5;
    if (customerCategory == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, customerCategory);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"customers",
        "milk_entries"}, false, new Callable<List<MonthlyCustomerReport>>() {
      @Override
      @Nullable
      public List<MonthlyCustomerReport> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCustomerId = 0;
          final int _cursorIndexOfCustomerName = 1;
          final int _cursorIndexOfCustomerType = 2;
          final int _cursorIndexOfTotalQuantity = 3;
          final int _cursorIndexOfTotalAmount = 4;
          final List<MonthlyCustomerReport> _result = new ArrayList<MonthlyCustomerReport>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MonthlyCustomerReport _item;
            final int _tmpCustomerId;
            _tmpCustomerId = _cursor.getInt(_cursorIndexOfCustomerId);
            final String _tmpCustomerName;
            if (_cursor.isNull(_cursorIndexOfCustomerName)) {
              _tmpCustomerName = null;
            } else {
              _tmpCustomerName = _cursor.getString(_cursorIndexOfCustomerName);
            }
            final String _tmpCustomerType;
            if (_cursor.isNull(_cursorIndexOfCustomerType)) {
              _tmpCustomerType = null;
            } else {
              _tmpCustomerType = _cursor.getString(_cursorIndexOfCustomerType);
            }
            final double _tmpTotalQuantity;
            _tmpTotalQuantity = _cursor.getDouble(_cursorIndexOfTotalQuantity);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            _item = new MonthlyCustomerReport(_tmpCustomerId,_tmpCustomerName,_tmpCustomerType,_tmpTotalQuantity,_tmpTotalAmount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<DailySessionBreakdown>> getDailySessionBreakdown(final String date,
      final String entryType, final Integer customerId) {
    final String _sql = "\n"
            + "        SELECT\n"
            + "            session,\n"
            + "            IFNULL(SUM(quantityLiters), 0) AS totalQuantity,\n"
            + "            IFNULL(SUM(amount), 0) AS totalAmount,\n"
            + "            COUNT(*) AS entryCount\n"
            + "        FROM milk_entries\n"
            + "        WHERE entryDate = ?\n"
            + "          AND (? IS NULL OR entryType = ?)\n"
            + "          AND (? IS NULL OR customerId = ?)\n"
            + "        GROUP BY session\n"
            + "        ORDER BY CASE WHEN session = 'MORNING' THEN 0 ELSE 1 END\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 2;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 3;
    if (entryType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, entryType);
    }
    _argIndex = 4;
    if (customerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, customerId);
    }
    _argIndex = 5;
    if (customerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, customerId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"milk_entries"}, false, new Callable<List<DailySessionBreakdown>>() {
      @Override
      @Nullable
      public List<DailySessionBreakdown> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSession = 0;
          final int _cursorIndexOfTotalQuantity = 1;
          final int _cursorIndexOfTotalAmount = 2;
          final int _cursorIndexOfEntryCount = 3;
          final List<DailySessionBreakdown> _result = new ArrayList<DailySessionBreakdown>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailySessionBreakdown _item;
            final String _tmpSession;
            if (_cursor.isNull(_cursorIndexOfSession)) {
              _tmpSession = null;
            } else {
              _tmpSession = _cursor.getString(_cursorIndexOfSession);
            }
            final double _tmpTotalQuantity;
            _tmpTotalQuantity = _cursor.getDouble(_cursorIndexOfTotalQuantity);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final int _tmpEntryCount;
            _tmpEntryCount = _cursor.getInt(_cursorIndexOfEntryCount);
            _item = new DailySessionBreakdown(_tmpSession,_tmpTotalQuantity,_tmpTotalAmount,_tmpEntryCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
