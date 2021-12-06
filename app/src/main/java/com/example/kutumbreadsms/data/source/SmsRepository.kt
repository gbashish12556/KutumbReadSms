package com.example.navigithubpr.data.source

import com.example.kutumbreadsms.data.SectionData

interface SmsRepository {

    suspend fun getSms(forceUpdate:Boolean): List<SectionData>
}
