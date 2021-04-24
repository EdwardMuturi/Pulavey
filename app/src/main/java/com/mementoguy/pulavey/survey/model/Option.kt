package com.mementoguy.pulavey.survey.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Edward Muturi on 22/04/2021.
 */
data class Option (val value: String,
                   @SerializedName("display_text")
                   val displayText: String)
