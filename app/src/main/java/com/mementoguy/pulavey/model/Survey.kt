package com.mementoguy.pulavey.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Survey(
    @PrimaryKey
    val id: String,
    val questions: List<Question>,
    @SerializedName("start_question_id")
    val startQuestionId: String,
    val strings: Strings
)