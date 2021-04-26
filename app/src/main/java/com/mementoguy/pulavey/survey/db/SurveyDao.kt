package com.mementoguy.pulavey.survey.db

import androidx.room.*
import com.mementoguy.pulavey.survey.model.Response
import com.mementoguy.pulavey.survey.model.Survey

/**
 * Created by Edward Muturi on 25/04/2021.
 */

@Dao
interface SurveyDao {

    @Query("SELECT * FROM Survey")
    suspend fun findAllSurveys() : List<Survey>

    @Query("SELECT * FROM Survey WHERE id =:Id")
    suspend fun findSurveyById(Id : String) : Survey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSurvey(survey: Survey)

    @Query("SELECT * FROM Response WHERE uploadTime != null")
    suspend fun findOfflineResponses() : List<Response>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveResponse(response: Response)

    @Update
    suspend fun updateResponse(response: Response)
}