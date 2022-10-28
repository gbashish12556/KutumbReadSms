package com.example.kutumbreadsms

import android.app.Application
import com.example.navigithubpr.data.source.SmsRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp()
class SmsApplication:Application() {

    val smsRepository: SmsRepository
        get() = ServiceLocator.provideTasksRepository(this)

    override fun onCreate() {
        super.onCreate()
    }

}