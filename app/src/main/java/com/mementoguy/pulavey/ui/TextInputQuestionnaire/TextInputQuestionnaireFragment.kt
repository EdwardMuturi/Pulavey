package com.mementoguy.pulavey.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.mementoguy.pulavey.databinding.FragmentTextInputQuestionnaireBinding
import com.mementoguy.pulavey.model.Questionnaire
import com.mementoguy.pulavey.model.toMap
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TextInputQuestionnaireFragment : Fragment() {

    private var mutableBinding: FragmentTextInputQuestionnaireBinding? = null
    private val binding get() = mutableBinding!!

    private val surveyViewModel by sharedViewModel<SurveyViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mutableBinding = FragmentTextInputQuestionnaireBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayQuestion()
        displayNextQuestion()
    }

    private fun displayQuestion(){
        val argString= requireArguments().getString("questionnaire")
        val questionnaire= Gson().fromJson(argString, Questionnaire::class.java)
        val enMap= questionnaire.en.toMap()
        binding.tvQuestion.text= enMap[questionnaire.question.questionText]
    }

    private fun displayNextQuestion(){
        binding.btnNext.setOnClickListener {
            getResponse()
        }
    }

    private fun getResponse(){
        Log.e(TextInputQuestionnaireFragment::class.simpleName, "getResponse: ${binding.tietResponse.text.toString().trim()}")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        mutableBinding = null
    }

}