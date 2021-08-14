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

}