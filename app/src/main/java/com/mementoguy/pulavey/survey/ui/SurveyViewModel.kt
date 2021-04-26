package com.mementoguy.pulavey.survey.ui

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.work.*
import com.google.gson.Gson
import com.mementoguy.pulavey.survey.data.SurveyRepository
import com.mementoguy.pulavey.survey.model.Question
import com.mementoguy.pulavey.survey.model.Response
import com.mementoguy.pulavey.survey.workers.SaveResponsesWorker
import com.mementoguy.pulavey.survey.workers.SyncOfflineResponsesWorker
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/**
 * Created by Edward Muturi on 24/04/2021.
 */
class SurveyViewModel(private val surveyRepository: SurveyRepository, application: Application) : AndroidViewModel(application) {

    private val mutableQuestions: MutableLiveData<List<Question>> = MutableLiveData()
    val questions : LiveData<List<Question>> get() = mutableQuestions
    private val workManager= WorkManager.getInstance(application)

    init {
        uploadResponses()
    }

    fun syncDataFromServer(){
        viewModelScope.launch {
            surveyRepository.loadDataFromServer()
        }
    }

    fun loadSurvey(position: Int){
        viewModelScope.launch {
            val survey= surveyRepository.fetchSurveyList()[position]
            mutableQuestions.value= survey.questions
        }
    }

    fun saveResponses(responses: List<Response>){
        val responsesData= workDataOf("responses" to Gson().toJson(responses))
        val saveResponseWorkRequest= OneTimeWorkRequestBuilder<SaveResponsesWorker>()
            .setInputData(responsesData)
            .build()

        workManager.beginUniqueWork("SAVE_RESPONSES_WORK", ExistingWorkPolicy.KEEP ,saveResponseWorkRequest).enqueue()
    }

    private fun uploadResponses(){
        val constraints= Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadResponsesWorker= PeriodicWorkRequestBuilder<SyncOfflineResponsesWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork("RESPONSE_SYNC_WORK", ExistingPeriodicWorkPolicy.KEEP, uploadResponsesWorker)
    }

}