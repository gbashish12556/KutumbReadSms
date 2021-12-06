package com.example.kutumbreadsms.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "section_data")
data class SectionData(
    @PrimaryKey
    val id: Int,
    val sectionName: String?,
    val data: MutableList<SmsData>
):Serializable
