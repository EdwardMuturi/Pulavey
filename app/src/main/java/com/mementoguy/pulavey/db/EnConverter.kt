package com.mementoguy.pulavey.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mementoguy.pulavey.model.En

class EnConverter {
    @TypeConverter
    fun fromEn(en: En) : String{
        val type= object : TypeToken<En>(){}.type
        return Gson().toJson(en, type)
    }

    @TypeConverter
    fun toEn(jsonString: String) : En{
        val type= object : TypeToken<En>(){}.type
        return Gson().fromJson(jsonString, type)
    }

}