package com.example.truecreditslist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kutumbreadsms.data.SectionData


@Database(entities = [SectionData::class], version = 1, exportSchema = false)
abstract class SmsLocalDb : RoomDatabase() {

    abstract fun smsDao(): SmsDao

}