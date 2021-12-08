package com.example.kutumbreadsms.data

import androidx.room.*
import com.example.kutumbreadsms.data.source.db.SmsDataConverter
import java.io.Serializable
@Entity(tableName = "section_table")
data class SectionData(

    @PrimaryKey
    val id: Int,
    val sectionName: String?,
    var hasData: Boolean,
    @TypeConverters(SmsDataConverter::class)
    val data: MutableList<SmsData>

):Serializable
