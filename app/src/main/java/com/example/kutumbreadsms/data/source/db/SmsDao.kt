package com.example.truecreditslist.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kutumbreadsms.data.SectionData

@Dao
interface SmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<SectionData>)

    @Query("SELECT * FROM section_data")
    fun allPosts(): LiveData<List<SectionData>>

    @Query("DELETE FROM section_data")
    suspend fun deleteSms()

}
