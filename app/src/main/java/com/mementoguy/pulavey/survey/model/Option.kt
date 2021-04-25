package com.mementoguy.pulavey.survey.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Created by Edward Muturi on 22/04/2021.
 */
@Entity
data class Option (val value: String,
                   @SerializedName("display_text")
                   val displayText: String)
