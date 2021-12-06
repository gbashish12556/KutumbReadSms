package com.example.navigithubpr.data.source

import com.example.kutumbreadsms.data.SectionData


interface SmsLocalDataSource {
    suspend fun getSms(): List<SectionData>
    suspend fun deleteAllSms()
    suspend fun insertSms(smsList:List<SectionData>)
}