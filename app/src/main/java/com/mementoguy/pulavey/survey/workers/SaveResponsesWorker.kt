package com.mementoguy.pulavey.survey.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mementoguy.pulavey.survey.data.SurveyRepositoryImpl
import com.mementoguy.pulavey.survey.model.Question
import com.mementoguy.pulavey.survey.model.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by Edward Muturi on 26/04/2021.
 */
class SaveResponsesWorker(appContext: Context, workerParameters: WorkerParameters) : CoroutineWorker(appContext, workerParameters), KoinComponent{

    private val surveyRepository: SurveyRepositoryImpl by inject()

    override suspend fun doWork(): Result {
        return try {
            saveResponses(getResponses())
            Result.success()
        }catch (exception: Exception){
            Result.failure()
        }
    }

    private fun getResponses(): List<Response> {
        val responseString = inputData.getString("responses")
        val type = object : TypeToken<List<Question>>() {}.type

        return Gson().fromJson(responseString, type)
    }

    private suspend fun saveResponses(responses: List<Response>) {
        responses.forEach { response ->
            surveyRepository.saveResponse(response)
        }
    }
}