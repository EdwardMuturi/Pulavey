package com.mementoguy.pulavey.survey.data

import com.mementoguy.pulavey.survey.model.Response
import com.mementoguy.pulavey.survey.model.Survey

/**
 * Created by Edward Muturi on 26/04/2021.
 */
interface SurveyRepository {
    suspend fun loadDataFromServer()
    suspend fun saveSurvey(survey: Survey)
    suspend fun fetchSurveyFromServer(): Survey
    suspend fun fetchSurveyList(): List<Survey>
    suspend fun findSurveyById(Id: String) : Survey

    suspend fun saveResponse(response: Response)
    suspend fun updateResponse(response: Response)
    suspend fun uploadResponse(response: Response)
    suspend fun fetchResponsesNotUploaded(): List<Response>
}