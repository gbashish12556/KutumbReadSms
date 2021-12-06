package com.example.truecreditslist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kutumbreadsms.data.SectionData


@Database(
    entities = [SectionData::class],
    version = 1,
    exportSchema = false
)
abstract class SmsLocalDb : RoomDatabase() {
    companion object {
        fun create(context: Context): SmsLocalDb {
            val databaseBuilder = Room.databaseBuilder(context, SmsLocalDb::class.java, "sms_local.db")
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun smsDao(): SmsDao
}