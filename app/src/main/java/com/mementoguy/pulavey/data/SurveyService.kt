package com.mementoguy.pulavey.data

import com.mementoguy.pulavey.model.Survey
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Edward Muturi on 24/04/2021.
 */
interface SurveyService{
    @GET("v3/d628facc-ec18-431d-a8fc-9c096e00709a/")
    suspend fun fetchSurvey() : Response<Survey>
}