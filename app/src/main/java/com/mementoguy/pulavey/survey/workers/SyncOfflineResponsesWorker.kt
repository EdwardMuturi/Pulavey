package com.mementoguy.pulavey.survey.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.mementoguy.pulavey.survey.data.SurveyRepositoryImpl
import com.mementoguy.pulavey.survey.model.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

/**
 * Created by Edward Muturi on 26/04/2021.
 */
class SyncOfflineResponsesWorker (appContext: Context, workerParameters: WorkerParameters) : CoroutineWorker(appContext, workerParameters), KoinComponent {
    private val surveyRepositoryImpl: SurveyRepositoryImpl by inject()

    override suspend fun doWork(): Result {
        return try {
            findOfflineResponses().forEach { response ->
                surveyRepositoryImpl.uploadResponse(response)
                surveyRepositoryImpl.updateResponse(response.copy(uploadTime = Calendar.getInstance().time.toString()))
            }
            Result.success()
        }catch (exception: Exception){
            val outputData = workDataOf("SyncResponsesWorkerError" to exception.localizedMessage)
            Result.failure(outputData)
        }
    }

    private suspend fun findOfflineResponses(): List<Response> {
        return surveyRepositoryImpl.fetchResponsesNotUploaded()
    }
}