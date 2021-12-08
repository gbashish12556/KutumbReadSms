/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.kutumbreadsms.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.kutumbreadsms.R
import com.example.kutumbreadsms.data.SmsData

/**
 * Main activity for the todoapp. Holds the Navigation Host Fragment and the Drawer, Toolbar, etc.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
        if(intent != null && intent.extras != null){
            var smsData:SmsData = intent.extras!!.getSerializable("smsData") as SmsData
            var navController:NavController = Navigation.findNavController(this, R.id.nav_host_fragment)
            navController.navigate(R.id.action_messageListFragment2_to_mesageDetailFragment2, Bundle().apply {
                putSerializable("smsData", smsData)
            })
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(){
        val channel = NotificationChannel("SmsChannel",
            "Sms Channel",NotificationManager.IMPORTANCE_HIGH)
        channel.lightColor = Color.GREEN
        channel.description = "Sms Channel"
        val notificationManager: NotificationManager =
            this.getSystemService<NotificationManager>(
                NotificationManager::class.java
            )
        notificationManager.createNotificationChannel(channel)
    }

}
