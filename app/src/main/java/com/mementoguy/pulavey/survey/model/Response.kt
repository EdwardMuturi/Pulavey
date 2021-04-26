package com.mementoguy.pulavey.survey.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Edward Muturi on 26/04/2021.
 */
@Entity
data class Response(
    @PrimaryKey(autoGenerate = true)
    val Id: Int,
    val surveyId: String,
    val questionId: String,
    val value: String,
    val uploadTime: String? = null
)
