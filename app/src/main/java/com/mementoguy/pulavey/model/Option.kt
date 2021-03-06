package com.mementoguy.pulavey.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Option(
    @PrimaryKey(autoGenerate = true)
    val Id: Int,
    @SerializedName("display_text")
    val displayText: String,
    val value: String
)