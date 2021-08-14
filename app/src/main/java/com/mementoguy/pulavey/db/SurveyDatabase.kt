package com.mementoguy.pulavey.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mementoguy.pulavey.model.*

/**
 * Created by Edward Muturi on 25/04/2021.
 */
@Database(entities = [Survey::class, Question::class, Option::class, Response::class, En::class, Strings::class], version = 1, exportSchema = false)
@TypeConverters(QuestionConverter::class, OptionConverter::class, StringsConverter::class, EnConverter::class)
abstract class SurveyDatabase : RoomDatabase() {
    abstract val surveyDao : SurveyDao
}