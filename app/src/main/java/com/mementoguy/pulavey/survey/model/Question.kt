package com.mementoguy.pulavey.survey.model

/**
 * Created by Edward Muturi on 22/04/2021.
 */
data class Question (val id: String, val questionType: String, val answerType: String, val questionText: String,
    val options: List<Option>,
    val next: String? = null
)
