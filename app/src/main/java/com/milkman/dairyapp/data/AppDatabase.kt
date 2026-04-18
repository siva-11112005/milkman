package com.milkman.dairyapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.milkman.dairyapp.data.dao.AuditLogDao
import com.milkman.dairyapp.data.dao.CustomerDao
import com.milkman.dairyapp.data.dao.MilkEntryDao
import com.milkman.dairyapp.data.dao.SyncQueueDao
import com.milkman.dairyapp.data.dao.UserDao
import com.milkman.dairyapp.data.entity.AuditLogEntity
import com.milkman.dairyapp.data.entity.CustomerEntity
import com.milkman.dairyapp.data.entity.MilkEntryEntity
import com.milkman.dairyapp.data.entity.SyncQueueEntity
import com.milkman.dairyapp.data.model.User
import com.milkman.dairyapp.util.AppConstants

@Database(
    entities = [
        User::class,
        CustomerEntity::class,
        MilkEntryEntity::class,
        AuditLogEntity::class,
        SyncQueueEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun customerDao(): CustomerDao
    abstract fun milkEntryDao(): MilkEntryDao
    abstract fun auditLogDao(): AuditLogDao
    abstract fun syncQueueDao(): SyncQueueDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    AppConstants.DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
