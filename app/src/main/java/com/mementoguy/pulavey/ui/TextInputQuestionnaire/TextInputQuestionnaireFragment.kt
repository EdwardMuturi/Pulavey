package com.mementoguy.pulavey.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.gson.Gson
import com.mementoguy.pulavey.R
import com.mementoguy.pulavey.databinding.FragmentTextInputQuestionnaireBinding
import com.mementoguy.pulavey.model.Questionnaire
import com.mementoguy.pulavey.model.toMap
import com.mementoguy.pulavey.ui.RadioInputQuestionnaire.RadioQuestionnaireFragment
import com.mementoguy.pulavey.util.Constants
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
        val argString= requireArguments().getString(Constants.QUESTIONNAIRE_BUNDLE)
        val questionnaire= Gson().fromJson(argString, Questionnaire::class.java)

        displayQuestion(questionnaire)
        when(questionnaire.question.next == null){
            true->  finishSurvey(questionnaire)
            false-> displayNextQuestion(questionnaire)
        }
    }

    private fun finishSurvey(questionnaire: Questionnaire){
        binding.btnNext.visibility= View.GONE
        binding.btnFinish.visibility= View.VISIBLE

        binding.btnFinish.setOnClickListener {
            getResponse()
        }
    }

    private fun displayQuestion(questionnaire: Questionnaire) {
        val enMap= questionnaire.en.toMap()
        binding.tvQuestion.text= enMap[questionnaire.question.questionText]
    }

    private fun displayNextQuestion(questionnaire: Questionnaire) {
        binding.btnNext.setOnClickListener {
            getResponse()
            loadNextQuestion(questionnaire)
        }
    }

    private fun getResponse(){
        Log.e(TextInputQuestionnaireFragment::class.simpleName, "getResponse: ${binding.tietResponse.text.toString().trim()}")
    }

    private fun loadNextQuestion(questionnaire: Questionnaire){
        surveyViewModel.questionnaires.observe(viewLifecycleOwner, {questionnaires ->
            val nextQuestionnaire= questionnaires.find {qn ->
                questionnaire.question.next == qn.question.id
            }
            if (nextQuestionnaire != null)
                navigateToNextQuestionnaire(nextQuestionnaire)
        })
    }

    private fun navigateToNextQuestionnaire(nextQuestionnaire: Questionnaire) {
        val bundle= bundleOf(Constants.QUESTIONNAIRE_BUNDLE to Gson().toJson(nextQuestionnaire) )

        when (nextQuestionnaire.question.questionType  == "SELECT_ONE"){
            true ->{
                requireActivity().supportFragmentManager.commit {
                    replace<RadioQuestionnaireFragment>(R.id.fragmentContainer, args = bundle)
                }
            }
            false ->{
                requireActivity().supportFragmentManager.commit {
                    replace<TextInputQuestionnaireFragment>(R.id.fragmentContainer,  args = bundle)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mutableBinding = null
    }

}