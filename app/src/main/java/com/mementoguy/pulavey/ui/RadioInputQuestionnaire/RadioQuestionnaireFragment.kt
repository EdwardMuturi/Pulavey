package com.mementoguy.pulavey.ui.RadioInputQuestionnaire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mementoguy.pulavey.R
import com.mementoguy.pulavey.ui.SurveyViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RadioQuestionnaireFragment : Fragment() {

    private val surveyViewModel by sharedViewModel<SurveyViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radio_questionnaire, container, false)
    }

}