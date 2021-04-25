package com.mementoguy.pulavey.survey.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mementoguy.pulavey.survey.model.Survey

/**
 * Created by Edward Muturi on 25/04/2021.
 */

@Dao
interface SurveyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSurvey(survey: Survey)
}