package com.mementoguy.pulavey.survey.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mementoguy.pulavey.survey.data.SurveyRepository
import com.mementoguy.pulavey.survey.model.Option
import com.mementoguy.pulavey.survey.model.Question
import kotlinx.coroutines.launch

/**
 * Created by Edward Muturi on 24/04/2021.
 */
class SurveyViewModel(private val surveyRepository: SurveyRepository) : ViewModel() {

    private val mutableQuestions: MutableLiveData<List<Question>> = MutableLiveData()
    val questions : LiveData<List<Question>> get() = mutableQuestions

    fun loadSurvey(){
        viewModelScope.launch {
            mutableQuestions.value= surveyRepository.fetchSurveyFromServer().questions
        }
    }

}