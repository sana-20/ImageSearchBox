package com.example.imagesearchbox.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.imagesearchbox.utils.Constants.DATABASE_NAME

@Database(entities = [MyBox::class], version = 3)
abstract class MyBoxDatabase : RoomDatabase() {

    abstract fun myBoxDao(): MyBoxDao

    companion object {
        @Volatile
        private var database: MyBoxDatabase? = null

        fun getDatabase(
            context: Context): MyBoxDatabase {
            return database ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyBoxDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                database = instance
                instance
            }
        }

    }
}
