package com.jam.mvvmdemojaiminsarvan.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remotekeys")
data class RemoteKey(
    @PrimaryKey val id:String,
    val nextPage: Int?
)
