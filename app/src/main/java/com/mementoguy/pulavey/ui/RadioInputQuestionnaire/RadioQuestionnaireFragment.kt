package com.mementoguy.pulavey.ui.RadioInputQuestionnaire

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.gson.Gson
import com.mementoguy.pulavey.R
import com.mementoguy.pulavey.databinding.FragmentRadioQuestionnaireBinding
import com.mementoguy.pulavey.model.Question
import com.mementoguy.pulavey.model.Questionnaire
import com.mementoguy.pulavey.model.toMap
import com.mementoguy.pulavey.ui.SurveyViewModel
import com.mementoguy.pulavey.ui.TextInputQuestionnaireFragment
import com.mementoguy.pulavey.util.Constants
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RadioQuestionnaireFragment : Fragment() {

    private var _binding : FragmentRadioQuestionnaireBinding?= null
    private val binding get() = _binding!!

    private val surveyViewModel by sharedViewModel<SurveyViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentRadioQuestionnaireBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argString= requireArguments().getString("questionnaire")
        val questionnaire= Gson().fromJson(argString, Questionnaire::class.java)

        displayQuestion(questionnaire)
        setRadioButtonsLabel(questionnaire)

        when(questionnaire.question.next == null){
            true->  finishSurvey(questionnaire)
            false-> displayNextQuestion(questionnaire)
        }
    }

    private fun finishSurvey(questionnaire: Questionnaire){
        binding.btnNext.visibility= View.GONE
        binding.btnFinish.visibility= View.VISIBLE

        binding.btnFinish.setOnClickListener {
            getResponse(questionnaire.question)
        }
    }

    private fun displayQuestion(questionnaire: Questionnaire) {
        val enMap= questionnaire.en.toMap()
        binding.tvQuestion.text= enMap[questionnaire.question.questionText]
    }


    private fun setRadioButtonsLabel(questionnaire: Questionnaire){
        val enMap = questionnaire.en.toMap()
        with(binding){
            radioButton1.text = enMap[questionnaire.question.options[0].displayText]
            radioButton2.text = enMap[questionnaire.question.options[1].displayText]
            radioButton3.text = enMap[questionnaire.question.options[2].displayText]
        }
    }

    private fun displayNextQuestion(questionnaire: Questionnaire) {
        binding.btnNext.setOnClickListener {
            getResponse(questionnaire.question)
            loadNextQuestion(questionnaire)
        }
    }

    private fun getResponse(question: Question) {
        val tag= RadioQuestionnaireFragment::class.simpleName
        with(binding){
            radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
                when(checkedId){
                    radioButton1.id ->{ Log.e(tag, "selected Choice ${question.options[0].value}") }
                    radioButton2.id ->{ Log.e(tag, "selected Choice ${question.options[1].value}") }
                    radioButton3.id ->{ Log.e(tag, "selected Choice ${question.options[2].value}") }
                }
            }
        }
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
        _binding= null
    }

}