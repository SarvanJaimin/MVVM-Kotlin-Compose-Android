package com.jam.mvvmdemojaiminsarvan.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jam.mvvmdemojaiminsarvan.data.models.RemoteKey

@Dao
interface RemoteKeyDAO {

    @Query("SELECT * FROM remotekeys Where id =:id")
    suspend fun getRemoteKey(id: String): RemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(remoteKey: RemoteKey)

    @Query("DELETE FROM remotekeys")
    suspend fun clearAllRemoteKeys()

}