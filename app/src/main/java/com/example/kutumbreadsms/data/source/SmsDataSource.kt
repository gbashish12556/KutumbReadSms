package com.example.navigithubpr.data.source

import com.example.kutumbreadsms.data.SectionData


interface SmsDataSource {

    fun getSms(): List<SectionData>

}