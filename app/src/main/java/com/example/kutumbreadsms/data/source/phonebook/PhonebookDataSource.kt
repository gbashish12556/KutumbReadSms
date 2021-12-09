package com.example.kutumbreadsms.data.source.phonebook

import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.kutumbreadsms.SmsApplication
import com.example.kutumbreadsms.data.SectionData
import com.example.kutumbreadsms.data.SmsData
import com.example.kutumbreadsms.data.source.SmsRemoteDataSource
import com.example.kutumbreadsms.util.Util
import java.util.*

class PhonebookDataSource(val smsApplication: SmsApplication):SmsRemoteDataSource {
    override suspend fun getSms(): List<SectionData> {
        return createSectionData()
    }

    fun createSectionData(): List<SectionData> {
        try {
            val sectionData :MutableList<SectionData> = Util.intialiseSectionData()
            var smsUri:Uri = Uri.parse("content://sms/inbox")
            val cursor: Cursor? = smsApplication.getContentResolver().query(smsUri, null, null, null, null)
            if (cursor!!.moveToFirst()) {
                val cursorCount = cursor!!.count
                for (i in 0..cursorCount - 1) {

                    var messageDate: String =
                        cursor!!.getString(cursor.getColumnIndexOrThrow("date"))
                    var timestamp = messageDate.toLong()
                    val date = Date()
                    val timeNow: Long = date.getTime()
                    var timeStampDifference: Long = (timeNow - timestamp) / (60 * 60 * 1000)
                    if (!sectionData[Util.getIndex(timeStampDifference)].hasData) {
                        sectionData[Util.getIndex(timeStampDifference)].hasData = true
                    }
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
            return getFilteredList(sectionData);
        }catch (e:Exception){
            return emptyList()
        }
    }

    fun getFilteredList(sectionData:List<SectionData>):List<SectionData>{
        var listSize = sectionData.size
        var newList = mutableListOf<SectionData>()
        for(i in 0..listSize-1){
            if(sectionData[i].hasData){
                newList.add(sectionData[i])
            }
        }
        return newList;
    }

}