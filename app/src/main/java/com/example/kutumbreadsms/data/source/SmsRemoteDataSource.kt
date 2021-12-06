package com.example.kutumbreadsms.data.source

import com.example.kutumbreadsms.data.SectionData

interface SmsRemoteDataSource {

    suspend fun getSms(): List<SectionData>
}