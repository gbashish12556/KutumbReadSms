package com.example.kutumbreadsms.data.source.phonebook

import android.database.Cursor
import com.example.kutumbreadsms.data.SectionData
import com.example.kutumbreadsms.data.SmsData
import com.example.kutumbreadsms.data.source.SmsRemoteDataSource
import com.example.kutumbreadsms.util.Util
import java.util.*

class PhonebookDataSource(val cursor: Cursor?):SmsRemoteDataSource {
    val sectionData :MutableList<SectionData> = Util.intialiseSectionData()
    override suspend fun getSms(): List<SectionData> {
        return createSectionData()
    }

    fun createSectionData(): List<SectionData> {
        var messageDate: String = cursor!!.getString(cursor.getColumnIndexOrThrow("date"))
        var timestamp = messageDate.toLong()
        val date = Date()
        val timeNow: Long = date.getTime()
        var timeStampDifference: Long = (timeNow - timestamp)/(60*60*1000)
        if (cursor.moveToFirst()) {
            val cursorCount = cursor!!.count
            for (i in 0..cursorCount) {
                sectionData[Util.getIndex(timeStampDifference)].data.add(
                    SmsData(
                        id = cursor.getString(cursor.getColumnIndexOrThrow("_id")).toInt(),
                        sender = cursor.getString(cursor.getColumnIndexOrThrow("address")),
                        message = cursor.getString(cursor.getColumnIndexOrThrow("body"))
                    )
                )
                cursor.moveToNext()
            }
        }
        return sectionData;
    }

}