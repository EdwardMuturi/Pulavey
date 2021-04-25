package com.mementoguy.pulavey.survey.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Created by Edward Muturi on 22/04/2021.
 */
@Entity
data class Question (val id: String,
                     @SerializedName("question_type")
                     val questionType: String,
                     @SerializedName("answer_type")
                     val answerType: String,
                     @SerializedName("question_text")
                     val questionText: String,
    val options: List<Option>,
    val next: String? = null
)
