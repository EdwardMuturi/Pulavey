package com.mementoguy.pulavey.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.mementoguy.pulavey.databinding.ActivitySurveyBinding
import com.mementoguy.pulavey.model.Question
import com.mementoguy.pulavey.model.Response
import com.mementoguy.pulavey.questionnaire.QuestionnareViewPagerAdapter
import com.mementoguy.pulavey.util.SurveySharePref
import org.koin.androidx.viewmodel.ext.android.viewModel

class SurveyActivity : AppCompatActivity() {

    private val questionnareViewPagerAdapter = QuestionnareViewPagerAdapter()
    private lateinit var binding: ActivitySurveyBinding
    private val surveyViewModel: SurveyViewModel by viewModel()
    private val loadingDialog= LoadingDialog(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        loadData()

        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        displayQuestions()
//        getResponseInput()
//        finishSurvey()
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
        binding.vpSurvey.isUserInputEnabled= false
        binding.vpSurvey.adapter = questionnareViewPagerAdapter
        questionnareViewPagerAdapter.submitList(questions)
    }

    private fun displayNextQuestion(questions: List<Question>) {
        with(binding) {
            btnNext.setOnClickListener {
                if (vpSurvey.currentItem != questions.size - 1)
                    vpSurvey.currentItem++
                else {
                   hideNextButton()
                }
            }
        }
    }

    private fun hideNextButton(){
        binding.btnFinish.visibility = View.VISIBLE
        binding.btnNext.visibility = View.GONE
    }

    private fun showNextButton(){
        binding.btnNext.visibility = View.VISIBLE
        binding.btnFinish.visibility = View.GONE
    }

    private fun finishSurvey(){
        binding.btnFinish.setOnClickListener {
            loadingDialog.startLoadingDialog()
            saveResponses()
            dismissLoader()
        }
    }

    private fun saveResponses(){
        val responses= listOf(Response(1,"surv1", "Qtn1", "yes"))
        surveyViewModel.saveResponses(responses)
    }

    private fun dismissLoader(){
        Handler().postDelayed({
            Toast.makeText(this, "Responses saved successfully", Toast.LENGTH_SHORT).show()
            binding.vpSurvey.currentItem= 0
            loadingDialog.dismissDialog()
            showNextButton()
        }, 1000)
    }

    private fun getResponseInput(){
    }

}