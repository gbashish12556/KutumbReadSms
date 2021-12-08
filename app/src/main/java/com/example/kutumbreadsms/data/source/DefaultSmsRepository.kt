package com.example.kutumbreadsms.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.kutumbreadsms.data.SectionData
import com.example.navigithubpr.data.source.SmsLocalDataSource
import com.example.navigithubpr.data.source.SmsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultSmsRepository(
    private val smsRemoteDataSource: SmsRemoteDataSource,
    private val smsLocalDataSource: SmsLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SmsRepository {


    private suspend fun updateTasksFromRemoteDataSource() {
        withContext(ioDispatcher) {
            val smsList = smsRemoteDataSource.getSms()
            Log.d("SectionData","3")
            if (smsList.size > 0) {
                Log.d("SectionData","4")
                smsLocalDataSource.deleteAndInsert(smsList)
            }
        }
    }


    override fun getSms(): LiveData<List<SectionData>> {
        return smsLocalDataSource.getSms()
    }

    override suspend fun refreshTask() {
        updateTasksFromRemoteDataSource()
    }

}