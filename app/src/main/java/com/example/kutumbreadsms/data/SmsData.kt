package com.example.kutumbreadsms.data

import java.io.Serializable


data class SmsData(
    val id: Int,
    val sender: String,
    val message: String?
):Serializable