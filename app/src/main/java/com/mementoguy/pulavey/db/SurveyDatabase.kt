package com.mementoguy.pulavey.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mementoguy.pulavey.model.Option
import com.mementoguy.pulavey.model.Question
import com.mementoguy.pulavey.model.Response
import com.mementoguy.pulavey.model.Survey

/**
 * Created by Edward Muturi on 25/04/2021.
 */
@Database(entities = [Survey::class, Question::class, Option::class, Response::class], version = 1, exportSchema = false)
@TypeConverters(QuestionConverter::class, OptionConverter::class)
abstract class SurveyDatabase : RoomDatabase() {
    abstract val surveyDao : SurveyDao
}