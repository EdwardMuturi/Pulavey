package com.mementoguy.pulavey.model

import com.google.gson.Gson

data class Questionnaire(val question: Question, val questionString: String)

fun Question.toQuestionnaire(en: En): Questionnaire {
    val enMap= en.toMap()
    return Questionnaire(this, enMap[this.questionText].toString())
}

fun En.toMap(): Map<String, String>{
    val enString= Gson().toJson(this)
    return Gson().fromJson(enString, Map::class.java) as Map<String, String>
}
