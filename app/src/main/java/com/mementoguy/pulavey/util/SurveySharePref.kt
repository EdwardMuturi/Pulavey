package com.mementoguy.pulavey.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit

/**
 * Created by Edward Muturi on 25/04/2021.
 */
object SurveySharePref {
    private lateinit var sharePref: SharedPreferences
    private const val IS_FIRST_LAUNCH= "isAppFirstLaunch"

    fun initialize(context: Context){
        sharePref= context.getSharedPreferences("SurveySharedPref", Context.MODE_PRIVATE)
    }

    fun updateIsFirstLaunch(){
        sharePref.edit {
            putBoolean(IS_FIRST_LAUNCH, false)
        }
    }

    fun checkIsAppFirstLaunch(): Boolean {
        return sharePref.getBoolean(IS_FIRST_LAUNCH, true)
    }
}