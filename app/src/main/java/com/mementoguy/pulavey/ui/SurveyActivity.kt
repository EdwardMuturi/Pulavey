package com.mementoguy.pulavey.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mementoguy.pulavey.databinding.ActivitySurveyBinding
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

}