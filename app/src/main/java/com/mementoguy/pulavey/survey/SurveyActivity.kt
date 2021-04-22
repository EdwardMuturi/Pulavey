package com.mementoguy.pulavey.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.mementoguy.pulavey.R
import com.mementoguy.pulavey.databinding.ActivitySurveyBinding
import com.mementoguy.pulavey.survey.model.Option
import com.mementoguy.pulavey.survey.model.Question
import com.mementoguy.pulavey.survey.questionnaire.QuestionnareViewPagerAdapter

private val questionnareViewPagerAdapter= QuestionnareViewPagerAdapter()
private lateinit var binding: ActivitySurveyBinding

class SurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO get list through viewmodel
        val questions= listOf<Question>(Question("q_gender", "SELECT_ONE", "SINGLE_LINE_TEXT",
            "What is your gender?", listOf(Option("MALE", "Male")) ),

            Question("q_name", "FREE_TEXT", "SINGLE_LINE_TEXT",
            "What is your name?", emptyList<Option>(), "q_age" ),

            Question("q_age", "FREE_TEXT", "INTEGER",
                "How old are you?", emptyList<Option>(),  "q_gender" )
        )

        binding.vpSurvey.adapter= questionnareViewPagerAdapter
        questionnareViewPagerAdapter.submitList(questions)

    }

}