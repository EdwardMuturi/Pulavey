package com.mementoguy.pulavey.survey.data

import android.util.Log
import com.mementoguy.pulavey.survey.model.Survey

/**
 * Created by Edward Muturi on 24/04/2021.
 */
interface SurveyRepository {
    suspend fun fetchSurveyFromServer(): Survey
}

class SurveyRepositoryImpl : SurveyRepository {
    val api= SurveyService.getSurveyService

    override suspend fun fetchSurveyFromServer(): Survey {
        val requestResult= api.fetchSurvey()
        return requestResult.body()!!
    }
}