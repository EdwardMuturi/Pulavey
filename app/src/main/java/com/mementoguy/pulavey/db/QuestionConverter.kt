package com.mementoguy.pulavey.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mementoguy.pulavey.model.Question

/**
 * Created by Edward Muturi on 25/04/2021.
 */
class QuestionConverter {
 @TypeConverter
 fun fromQuestionList(questions: List<Question>) : String {
     val type= object : TypeToken<List<Question>>() {}.type
     return Gson().toJson(questions, type)
 }

    @TypeConverter
    fun toQuestionList(questionString: String) : List<Question>{
        val type= object : TypeToken<List<Question>>() {}.type
        return Gson().fromJson(questionString, type)
    }
}