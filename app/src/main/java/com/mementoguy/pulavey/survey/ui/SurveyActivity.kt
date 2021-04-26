package com.mementoguy.pulavey.survey.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.mementoguy.pulavey.R
import com.mementoguy.pulavey.databinding.ActivitySurveyBinding
import com.mementoguy.pulavey.survey.model.Question
import com.mementoguy.pulavey.survey.model.Response
import com.mementoguy.pulavey.survey.questionnaire.QuestionnareViewPagerAdapter
import com.mementoguy.pulavey.util.SurveySharePref
import org.koin.androidx.viewmodel.ext.android.viewModel

class SurveyActivity : AppCompatActivity() {

    private val questionnareViewPagerAdapter = QuestionnareViewPagerAdapter()
    private lateinit var binding: ActivitySurveyBinding
    private val surveyViewModel: SurveyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadData()

        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestions()
        getResponseInput()
        saveResponses()
    }


    private fun loadData(){
        // assumes list of survey and position represents selected survey
        val surveyPosition= 0

        when (SurveySharePref.checkIsAppFirstLaunch()){
            false -> {
                surveyViewModel.syncDataFromServer()
                SurveySharePref.setFirstLaunch()
            }
            true -> {
                surveyViewModel.loadSurvey(surveyPosition)
                Log.i(SurveyActivity::class.simpleName, "Not App first launch, skipping data sync...")
            }
        }

    }

    private fun displayQuestions() {
        surveyViewModel.questions.observe(this, Observer { questions ->
            setUpQuestionnareViewPager(questions)
            displayNextQuestion(questions)
        })
    }

    private fun setUpQuestionnareViewPager(questions: List<Question>) {
        binding.vpSurvey.adapter = questionnareViewPagerAdapter
        questionnareViewPagerAdapter.submitList(questions)
    }

    private fun displayNextQuestion(questions: List<Question>) {
        with(binding) {
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

    private fun saveResponses(){
        binding.btnFinish.setOnClickListener {
            val responses= listOf(Response(1,"surv1", "Qtn1", "yes"))
            surveyViewModel.saveResponses(responses)
        }
    }

    private fun getResponseInput(){
    }

}