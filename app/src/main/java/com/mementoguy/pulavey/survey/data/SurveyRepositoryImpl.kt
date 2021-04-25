package com.mementoguy.pulavey.survey.data

import android.util.Log
import com.mementoguy.pulavey.survey.db.SurveyDao
import com.mementoguy.pulavey.survey.model.Survey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Edward Muturi on 24/04/2021.
 */
interface SurveyRepository {
    suspend fun loadDataFromServer()
    suspend fun fetchSurveyFromServer(): Survey
    suspend fun fetchSurveyList(): List<Survey>
    suspend fun findSurveyById(Id: String) : Survey
}

class SurveyRepositoryImpl(val surveyService: SurveyService, val surveyDao: SurveyDao) : SurveyRepository {
    override suspend fun loadDataFromServer() {
        val survey= fetchSurveyFromServer()
        surveyDao.saveSurvey(survey)
    }

    override suspend fun fetchSurveyFromServer(): Survey {
        val requestResult= surveyService.fetchSurvey()
        return requestResult.body()!!
    }

    override suspend fun fetchSurveyList(): List<Survey> {
        return surveyDao.findAllSurveys()
    }

    override suspend fun findSurveyById(Id: String): Survey {
        return surveyDao.findSurveyById(Id)
    }
}