package com.mementoguy.pulavey.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mementoguy.pulavey.model.Option

/**
 * Created by Edward Muturi on 25/04/2021.
 */
class OptionConverter {
    @TypeConverter
    fun fromString(value: String) : List<Option>{
        val list= object : TypeToken<List<Option>>() {}.type
        return Gson().fromJson(value, list)
    }

    @TypeConverter
    fun fromOptionList(options: List<Option>) : String{
        return Gson().toJson(options)
    }

}