package com.example.navigithubpr.data.source

import com.example.kutumbreadsms.data.SectionData

interface SmsRepository {

    fun getSms(): List<SectionData>
}
