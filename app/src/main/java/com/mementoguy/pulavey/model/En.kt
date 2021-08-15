package com.mementoguy.pulavey.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

@Entity
data class En(
    @SerializedName("opt_female")
    val optFemale: String,
    @SerializedName("opt_male")
    val optMale: String,
    @SerializedName("opt_other")
    val optOther: String,
    @SerializedName("q_farmer_gender")
    val qFarmerGender: String,
    @SerializedName("q_farmer_name")
    val qFarmerName: String,
    @SerializedName("q_size_of_farm")
    val qSizeOfFarm: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0
}

fun En.toMap(): Map<String, String>{
    val enString= Gson().toJson(this)
    return Gson().fromJson(enString, Map::class.java) as Map<String, String>
}