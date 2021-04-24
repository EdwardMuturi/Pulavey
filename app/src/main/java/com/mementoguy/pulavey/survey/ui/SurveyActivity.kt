package com.mementoguy.pulavey.survey.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mementoguy.pulavey.databinding.ActivitySurveyBinding
import com.mementoguy.pulavey.survey.model.Option
import com.mementoguy.pulavey.survey.model.Question
import com.mementoguy.pulavey.survey.questionnaire.QuestionnareViewPagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SurveyActivity : AppCompatActivity() {
private val questionnareViewPagerAdapter = QuestionnareViewPagerAdapter()
private lateinit var binding: ActivitySurveyBinding
private val surveyViewModel : SurveyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vpSurvey.adapter = questionnareViewPagerAdapter
        questionnareViewPagerAdapter.submitList(questions)
        displayNextQuestion(questions)

    }

    private fun displayNextQuestion(questions: List<Question>) {
        with(binding){
            btnNext.setOnClickListener {
                if (vpSurvey.currentItem != questions.size - 1)
                    vpSurvey.currentItem++
                else {
                    btnFinish.visibility = View.VISIBLE
                    it.visibility = View.GONE
                }
            }
        }
    }

}