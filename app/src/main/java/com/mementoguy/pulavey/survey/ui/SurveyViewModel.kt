package com.mementoguy.pulavey.survey.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.workDataOf
import com.google.gson.Gson
import com.mementoguy.pulavey.survey.data.SurveyRepository
import com.mementoguy.pulavey.survey.model.Question
import com.mementoguy.pulavey.survey.model.Response
import kotlinx.coroutines.launch

/**
 * Created by Edward Muturi on 24/04/2021.
 */
class SurveyViewModel(private val surveyRepository: SurveyRepository) : ViewModel() {

    private val mutableQuestions: MutableLiveData<List<Question>> = MutableLiveData()
    val questions : LiveData<List<Question>> get() = mutableQuestions

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
//        val saveResponseWorkRequest=

    }

}