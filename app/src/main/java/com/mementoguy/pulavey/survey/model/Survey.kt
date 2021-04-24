package com.mementoguy.pulavey.survey.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Edward Muturi on 23/04/2021.
 */
data class Survey(val id: String,
                  @SerializedName("start_question_id")
                  val startQuestionID: String, val questions: List<Question>)
