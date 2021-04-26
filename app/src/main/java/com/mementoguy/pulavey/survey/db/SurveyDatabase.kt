package com.mementoguy.pulavey.survey.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mementoguy.pulavey.survey.model.Option
import com.mementoguy.pulavey.survey.model.Question
import com.mementoguy.pulavey.survey.model.Response
import com.mementoguy.pulavey.survey.model.Survey

/**
 * Created by Edward Muturi on 25/04/2021.
 */
@Database(entities = [Survey::class, Question::class, Option::class, Response::class], version = 2, exportSchema = false)
@TypeConverters(QuestionConverter::class, OptionConverter::class)
abstract class SurveyDatabase : RoomDatabase() {
    abstract val surveyDao : SurveyDao
}