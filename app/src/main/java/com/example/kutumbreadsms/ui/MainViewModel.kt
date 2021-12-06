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


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.kutumbreadsms.data.SectionData
import com.example.navigithubpr.data.source.SmsRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for the task list screen.
 */
class MainViewModel(
    private val smsRepository: SmsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _forceUpdate = MutableLiveData<Boolean>(false)

    private val _items: LiveData<List<SectionData>> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            _dataLoading.value = true
            viewModelScope.launch {
                smsRepository.refreshTask()
                _dataLoading.value = false
            }
        }
        smsRepository.getSms().switchMap {
            filterTasks(it)
        }
    }

    private fun filterTasks(tasksResult: List<SectionData>): LiveData<List<SectionData>> {
        val result = MutableLiveData<List<SectionData>>()
        result.postValue(tasksResult)
        return result
    }

    val items: LiveData<List<SectionData>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

}


