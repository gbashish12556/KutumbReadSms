package com.example.kutumbreadsms.data.source.db

import androidx.lifecycle.LiveData
import com.example.kutumbreadsms.data.SectionData
import com.example.navigithubpr.data.source.SmsLocalDataSource
import com.example.truecreditslist.db.SmsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource internal constructor(
    private val smsDao: SmsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO ) : SmsLocalDataSource{

    override fun getSms(): LiveData<List<SectionData>> {
        return smsDao.allPosts()
    }

    override suspend fun deleteAllSms() = withContext(ioDispatcher){
        smsDao.deleteSms()
    }

    override suspend fun insertSms(smsList: List<SectionData>) = withContext(ioDispatcher){
        smsDao.insertAll(smsList)
    }

}