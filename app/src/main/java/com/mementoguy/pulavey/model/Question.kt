package com.mementoguy.pulavey.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Question(
    @SerializedName("answer_type")
    val answerType: String,
    @PrimaryKey
    val id: String,
    val next: String,
    val options: List<Option>,
    @SerializedName("question_text")
    val questionText: String,
    @SerializedName("question_type")
    val questionType: String
)