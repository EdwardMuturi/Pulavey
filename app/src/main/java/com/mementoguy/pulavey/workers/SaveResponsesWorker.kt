package com.mementoguy.pulavey.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mementoguy.pulavey.data.SurveyRepository
import com.mementoguy.pulavey.model.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by Edward Muturi on 26/04/2021.
 */
class SaveResponsesWorker(appContext: Context, workerParameters: WorkerParameters) : CoroutineWorker(appContext, workerParameters), KoinComponent{

    private val surveyRepository: SurveyRepository by inject()

    override suspend fun doWork(): Result {
        return try {
            saveResponses(getResponses())
            Result.success()
        }catch (exception: Exception){
            val outputData= workDataOf("savedResponsesError" to exception.localizedMessage)
            Result.failure(outputData)
        }
    }

    private fun getResponses(): List<Response> {
        val responseString = inputData.getString("responses")
        val type = object : TypeToken<List<Response>>() {}.type

        return Gson().fromJson(responseString, type)
    }

    private suspend fun saveResponses(responses: List<Response>) {
        responses.forEach { response ->
            surveyRepository.saveResponse(response)
        }
    }
}