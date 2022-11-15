package com.gg.database.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gg.database.db.dao.CurrenciesDao
import com.gg.database.db.entity.Currency


@Database(entities = [Currency::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currenciesDao(): CurrenciesDao

    companion object {
        val DATABASE_NAME = "app_db"
        private var INSTANCE: AppDatabase? = null
        fun getAppDatabase(application: Application): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        application,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE!!
        }
    }
}