package com.example.truecreditslist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kutumbreadsms.data.SectionData
import com.example.kutumbreadsms.data.source.db.SmsDataConverter


@Database(entities = [SectionData::class], version = 1, exportSchema = false)
@TypeConverters(SmsDataConverter::class)
abstract class SmsLocalDb : RoomDatabase() {

    abstract fun smsDao(): SmsDao

}