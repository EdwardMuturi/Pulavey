package com.mementoguy.pulavey.survey.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mementoguy.pulavey.survey.model.Option
import com.mementoguy.pulavey.survey.model.Question

/**
 * Created by Edward Muturi on 24/04/2021.
 */
class SurveyViewModel : ViewModel() {

    private val mutableQuestions: MutableLiveData<List<Question>> = MutableLiveData()
    val questions : LiveData<List<Question>> get() = mutableQuestions

    fun loadSurvey(){
        mutableQuestions.value= listOf<Question>(
            Question(
                "q_gender", "SELECT_ONE", "SINGLE_LINE_TEXT",
                "What is your gender?", listOf(Option("MALE", "Male"))
            ),

            Question(
                "q_name",
                "FREE_TEXT",
                "SINGLE_LINE_TEXT",
                "What is your name?",
                emptyList<Option>(),
                "q_age"
            ),

            Question(
                "q_age", "FREE_TEXT", "INTEGER",
                "How old are you?", emptyList<Option>(), "q_gender"
            )
        )
    }

}