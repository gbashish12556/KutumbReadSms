package com.example.kutumbreadsms.data.source.db

import androidx.room.TypeConverter
import com.example.kutumbreadsms.data.SmsData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class SmsDataConverter {
    @TypeConverter
    fun storedStringToMyObjects(data: String?): List<SmsData?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<SmsData?>?>() {}.getType()
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: List<SmsData?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}