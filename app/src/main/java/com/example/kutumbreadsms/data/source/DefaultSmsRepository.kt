package com.example.kutumbreadsms.data.source

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
        val remoteTasks = smsRemoteDataSource.getSms()
        if (remoteTasks.size > 0) {
            withContext(ioDispatcher) {
                smsLocalDataSource.deleteAllSms()
                smsLocalDataSource.insertSms(remoteTasks)
            }
        }
    }

    override suspend fun getSms(forceUpdate: Boolean): List<SectionData> {
        if (forceUpdate) {
            try {
                updateTasksFromRemoteDataSource()
            } catch (ex: Exception) {
                return emptyList()
            }
        }
        return smsLocalDataSource.getSms()
    }

}