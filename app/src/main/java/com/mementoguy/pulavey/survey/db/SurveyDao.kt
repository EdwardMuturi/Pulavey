package com.mementoguy.pulavey.survey.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mementoguy.pulavey.survey.model.Survey

/**
 * Created by Edward Muturi on 25/04/2021.
 */

@Dao
interface SurveyDao {

    @Query("SELECT * FROM Survey")
    fun findAllSurveys() : List<Survey>

    @Query("SELECT * FROM Survey WHERE id =:Id")
    fun findSurveyById(Id : String) : Survey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSurvey(survey: Survey)
}