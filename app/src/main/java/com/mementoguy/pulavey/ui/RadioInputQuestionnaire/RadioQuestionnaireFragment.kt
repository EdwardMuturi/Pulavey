package com.mementoguy.pulavey.ui.RadioInputQuestionnaire

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.mementoguy.pulavey.databinding.FragmentRadioQuestionnaireBinding
import com.mementoguy.pulavey.model.Question
import com.mementoguy.pulavey.model.Questionnaire
import com.mementoguy.pulavey.model.toMap
import com.mementoguy.pulavey.ui.SurveyViewModel
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
        displayNextQuestion(questionnaire)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }

}