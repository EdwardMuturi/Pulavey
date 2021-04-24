package com.mementoguy.pulavey.survey.data

/**
 * Created by Edward Muturi on 24/04/2021.
 */
interface SurveyRepository {
    fun fetchSurveyFromServer()
}

class SurveyRepositoryImpl : SurveyRepository {
    override fun fetchSurveyFromServer() {
        TODO("Not yet implemented")
    }
}