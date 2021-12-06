package com.example.navigithubpr.data.source

import androidx.lifecycle.LiveData
import com.example.kutumbreadsms.data.SectionData

interface SmsRepository {

    fun getSms(): LiveData<List<SectionData>>
    suspend fun refreshTask()
}
