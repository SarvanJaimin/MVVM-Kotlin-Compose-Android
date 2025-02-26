package com.jam.mvvmdemojaiminsarvan.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jam.mvvmdemojaiminsarvan.data.models.Source

class SourceTypeConverter {
    @TypeConverter
    fun fromSource(source: Source?): String {
        return Gson().toJson(source) // Convert Source to JSON String
    }

    @TypeConverter
    fun toSource(sourceString: String): Source {
        val type = object : TypeToken<Source>() {}.type
        return Gson().fromJson(sourceString, type) // Convert JSON String to Source
    }
}