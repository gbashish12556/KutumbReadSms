package com.example.navigithubpr.data.source

import androidx.lifecycle.LiveData
import com.example.kutumbreadsms.data.SectionData


interface SmsLocalDataSource {
    fun getSms(): LiveData<List<SectionData>>
    suspend fun deleteAllSms()
    suspend fun insertAllSms(smsList:List<SectionData>)
    suspend fun deleteAndInsert(smsList:List<SectionData>)
}