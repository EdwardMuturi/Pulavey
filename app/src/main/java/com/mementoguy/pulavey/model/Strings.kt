package com.mementoguy.pulavey.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Strings(
    @SerializedName("en")
    val en: En
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0
}