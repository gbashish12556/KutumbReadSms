package com.example.kutumbreadsms.util

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kutumbreadsms.SmsApplication
import com.example.kutumbreadsms.ViewModelFactory
import com.example.kutumbreadsms.ui.MainViewModel


fun Fragment.getViewModelFactory(activity: FragmentActivity, savedInstanceState:Bundle?): MainViewModel {
    val repository = (activity?.application as SmsApplication).smsRepository
    return activity?.run {
        val factory = ViewModelFactory(repository, this, savedInstanceState)
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    } ?: throw Exception("Invalid Activity")
}

fun FragmentActivity.getViewModelFactory(savedInstanceState:Bundle?): MainViewModel {
    val repository = (this?.application as SmsApplication).smsRepository
    return this?.run {
        val factory = ViewModelFactory(repository, this, savedInstanceState)
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    } ?: throw Exception("Invalid Activity")
}
