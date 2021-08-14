package com.mementoguy.pulavey.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mementoguy.pulavey.model.Strings

class StringsConverter {
    @TypeConverter
    fun fromStrings(strings: Strings) : String{
        val type= object : TypeToken<Strings>() {}.type
        return Gson().toJson(strings, type)
    }

    @TypeConverter
    fun toStrings(stringsJson: String) : Strings{
        val type= object : TypeToken<Strings>() {}.type
        return Gson().fromJson(stringsJson, type)
    }
}