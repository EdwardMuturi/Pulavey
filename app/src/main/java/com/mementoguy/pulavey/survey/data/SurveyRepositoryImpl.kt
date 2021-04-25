package com.mementoguy.pulavey.survey.data

import android.util.Log
import com.mementoguy.pulavey.survey.db.SurveyDao
import com.mementoguy.pulavey.survey.model.Survey

/**
 * Created by Edward Muturi on 24/04/2021.
 */
interface SurveyRepository {
    suspend fun fetchSurvey(): Survey
}

class SurveyRepositoryImpl(val surveyService: SurveyService, surveyDao: SurveyDao) : SurveyRepository {

    override suspend fun fetchSurvey(): Survey {
        val requestResult= surveyService.fetchSurvey()
        return requestResult.body()!!
    }
}