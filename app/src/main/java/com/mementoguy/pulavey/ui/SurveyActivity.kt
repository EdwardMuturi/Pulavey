package com.mementoguy.pulavey.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.mementoguy.pulavey.R
import com.mementoguy.pulavey.databinding.ActivitySurveyBinding
import com.mementoguy.pulavey.model.Questionnaire
import com.mementoguy.pulavey.util.SurveySharePref
import org.koin.androidx.viewmodel.ext.android.viewModel

class SurveyActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySurveyBinding
    private val surveyViewModel: SurveyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        loadQuestionnaire()
    }

    private fun loadData() {
        // assumes list of survey and position represents selected survey
        val surveyPosition = 0
        val tag = SurveyActivity::class.simpleName

        if (SurveySharePref.checkIsAppFirstLaunch()) {
            Log.i(tag, "Syncing data from server...")
            surveyViewModel.syncDataFromServer()
            SurveySharePref.updateIsFirstLaunch()
        }

        surveyViewModel.loadSurvey(surveyPosition)
    }

    private fun loadQuestionnaire(){
        surveyViewModel.questionnaires.observe(this, {
            when (it.first().question.questionType  == "SELECT_ONE"){
                true ->{
                    supportFragmentManager.commit {
                        add<RadioQuestionnareFragment>(R.id.fragmentContainer)
                    }
                }
                false ->{
                    supportFragmentManager.commit {
                        add<SurveyFragment>(R.id.fragmentContainer)
                    }
                }
            }
        })
    }

}