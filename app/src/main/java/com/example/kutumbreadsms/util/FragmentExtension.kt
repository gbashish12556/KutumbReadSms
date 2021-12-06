package com.example.kutumbreadsms.util

import androidx.fragment.app.Fragment
import com.example.kutumbreadsms.SmsApplication
import com.example.kutumbreadsms.ViewModelFactory


fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as SmsApplication).smsRepository
    return ViewModelFactory(repository, this)
}