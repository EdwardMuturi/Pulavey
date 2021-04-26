package com.mementoguy.pulavey.survey.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.mementoguy.pulavey.survey.data.SurveyRepositoryImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by Edward Muturi on 26/04/2021.
 */
class SaveResponsesWorker(appContext: Context, workerParameters: WorkerParameters) : CoroutineWorker(appContext, workerParameters), KoinComponent{

    val surveyRepository: SurveyRepositoryImpl by inject()

    override suspend fun doWork(): Result {
        return try {
            Result.success()
        }catch (exception: Exception){
            Result.failure()
        }
    }

    private fun saveResponses() {
        TODO("Not yet implemented")
    }
}