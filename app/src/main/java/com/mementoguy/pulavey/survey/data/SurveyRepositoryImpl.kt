package com.mementoguy.pulavey.survey.data

import android.util.Log
import com.mementoguy.pulavey.survey.db.SurveyDao
import com.mementoguy.pulavey.survey.model.Response
import com.mementoguy.pulavey.survey.model.Survey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Edward Muturi on 24/04/2021.
 */
class SurveyRepositoryImpl(val surveyService: SurveyService, val surveyDao: SurveyDao) : SurveyRepository {
    override suspend fun loadDataFromServer() {
        val survey= fetchSurveyFromServer()
        saveSurvey(survey)
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

    override suspend fun saveSurvey(survey: Survey) {
        surveyDao.saveSurvey(survey)
    }

    override suspend fun saveResponse(response: Response) {
        surveyDao.saveResponse(response)
    }

    override suspend fun updateResponse(response: Response) {
        surveyDao.updateResponse(response)
    }

    override suspend fun uploadResponse(response: Response) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchResponsesNotUploaded(): List<Response> {
        return surveyDao.findOfflineResponses()
    }
}