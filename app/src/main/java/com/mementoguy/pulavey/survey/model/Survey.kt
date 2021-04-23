package com.mementoguy.pulavey.survey.model

/**
 * Created by Edward Muturi on 23/04/2021.
 */
data class Survey(val id: String, val startQuestionID: String, val questions: List<Question>)
