package com.mementoguy.pulavey.survey.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Edward Muturi on 23/04/2021.
 */
@Entity
data class Survey(
    @PrimaryKey
    val id: String,
    @SerializedName("start_question_id")
    val startQuestionID: String,
    val questions: List<Question>
    )
