package com.example.kutumbreadsms

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kutumbreadsms.data.source.DefaultSmsRepository
import com.example.kutumbreadsms.data.source.SmsRemoteDataSource
import com.example.kutumbreadsms.data.source.db.RoomDataSource
import com.example.kutumbreadsms.data.source.phonebook.PhoneBookService
import com.example.kutumbreadsms.data.source.phonebook.PhonebookDataSource
import com.example.navigithubpr.data.source.SmsLocalDataSource
import com.example.navigithubpr.data.source.SmsRepository
import com.example.truecreditslist.db.SmsLocalDb


object ServiceLocator {

    private val lock = Any()
    private var database: SmsLocalDb? = null
    @Volatile
    var smsRepository: SmsRepository? = null
        @VisibleForTesting set


    fun provideTasksRepository(smsApplication: SmsApplication): SmsRepository {
        synchronized(this) {
            return smsRepository ?: smsRepository ?: createTasksRepository(smsApplication)
        }
    }

    private fun createTasksRepository(smsApplication: SmsApplication): SmsRepository {
        val newRepo = DefaultSmsRepository(createPrRemoteDataSource(smsApplication),createLocalDataSource(smsApplication))
        smsRepository = newRepo
        return newRepo
    }

    private fun createPrRemoteDataSource(smsApplication: SmsApplication): SmsRemoteDataSource {
        return PhonebookDataSource(smsApplication)
    }

    private fun createLocalDataSource(smsApplication: SmsApplication): SmsLocalDataSource {
        val database = database ?: createDataBase(smsApplication)
        return RoomDataSource(database.smsDao())
    }

    @VisibleForTesting
    fun createDataBase(
        context: Context,
    ): SmsLocalDb {
            // Real database using SQLite
            val result = Room.databaseBuilder(
                context.applicationContext,
                SmsLocalDb::class.java, "sms_local.db"
            ).build()
        database = result
        return result
    }


}