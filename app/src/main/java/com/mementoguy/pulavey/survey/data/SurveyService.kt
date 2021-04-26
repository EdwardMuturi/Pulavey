package com.mementoguy.pulavey.survey.data

import com.mementoguy.pulavey.survey.model.Survey
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Edward Muturi on 24/04/2021.
 */
interface SurveyService{
    @GET("api/v1/interview")
    suspend fun fetchSurvey() : Response<Survey>
}

const val base_url= "https://6049ea8cfb5dcc001796acdc.mockapi.io/"