package com.example.kutumbreadsms.data.source.db

import android.util.Log
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
        Log.d("SectionData","deleteSms")
        smsDao.deleteAllSms()
    }

    override suspend fun insertAllSms(smsList: List<SectionData>) = withContext(ioDispatcher){
        Log.d("SectionData","insertSms")
        smsDao.insertAll(smsList)
    }

    override suspend fun deleteAndInsert(smsList: List<SectionData>) {
        smsDao.deleteAllSms()
        smsDao.insertAll(smsList)
    }

}