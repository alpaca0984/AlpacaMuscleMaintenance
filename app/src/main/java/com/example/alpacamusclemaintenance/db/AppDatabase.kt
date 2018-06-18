package com.example.alpacamusclemaintenance.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import com.example.alpacamusclemaintenance.db.entity.PushUp
import com.example.alpacamusclemaintenance.util.DATABASE_NAME
import com.example.alpacamusclemaintenance.worker.SeedDatabaseWorker

@Database(
        entities = [
            PushUp::class],
        version = 2,
        exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pushUpDao(): PushUpDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequest.Builder(SeedDatabaseWorker::class.java).build()
                            WorkManager.getInstance().enqueue(request)
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}