package com.mementoguy.pulavey.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.google.gson.Gson
import com.mementoguy.pulavey.data.SurveyRepository
import com.mementoguy.pulavey.model.Question
import com.mementoguy.pulavey.model.Response
import com.mementoguy.pulavey.workers.SaveResponsesWorker
import com.mementoguy.pulavey.workers.SyncOfflineResponsesWorker
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
           try {
               val surveyList= surveyRepository.fetchSurveyList()
               if (surveyList.isEmpty())
                   throw IndexOutOfBoundsException("Empty Survey List")
               mutableQuestions.value= surveyList[position].questions
           }catch (e: IndexOutOfBoundsException){
               Log.e(SurveyViewModel::class.simpleName, "Empty Survey List")
           }
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