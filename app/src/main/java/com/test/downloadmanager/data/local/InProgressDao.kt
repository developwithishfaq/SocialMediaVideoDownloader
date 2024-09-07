package com.test.downloadmanager.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.test.downloadmanager.data.model.InProgressVideo
import kotlinx.coroutines.flow.Flow

@Dao
interface InProgressDao {

    @Upsert
    suspend fun addInProgress(inProgressVideo: InProgressVideo)

    @Query("DELETE FROM InProgressVideo WHERE id =:id")
    suspend fun deleteFromProgress(id: Long)

    @Query("SELECT * FROM InProgressVideo")
    suspend fun getAllInProgressVideos(): List<InProgressVideo>

    @Query("SELECT * FROM InProgressVideo")
    fun getAllInProgressVideosFlow(): Flow<List<InProgressVideo>>


}