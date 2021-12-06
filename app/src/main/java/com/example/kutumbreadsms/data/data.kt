package com.example.kutumbreadsms.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "section_data")
data class SectionData(
    @PrimaryKey
    val id: Int,
    val sectionName: String,
    val data: List<SmsData>
)


data class SmsData(
    val id: Int,
    val sender: String,
    val message: String?
)