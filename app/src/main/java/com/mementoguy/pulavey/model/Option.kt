package com.mementoguy.pulavey.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Edward Muturi on 22/04/2021.
 */
@Entity
data class Option (
    @PrimaryKey(autoGenerate = true)
    val Id: Int,
    val value: String,
                   @SerializedName("display_text")
                   val displayText: String)
