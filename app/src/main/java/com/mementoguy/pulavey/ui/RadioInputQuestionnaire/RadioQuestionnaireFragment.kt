package com.mementoguy.pulavey.ui.RadioInputQuestionnaire

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.gson.Gson
import com.mementoguy.pulavey.R
import com.mementoguy.pulavey.databinding.FragmentRadioQuestionnaireBinding
import com.mementoguy.pulavey.model.Question
import com.mementoguy.pulavey.model.Questionnaire
import com.mementoguy.pulavey.model.Response
import com.mementoguy.pulavey.model.toMap
import com.mementoguy.pulavey.ui.SurveyViewModel
import com.mementoguy.pulavey.ui.TextInputQuestionnaireFragment
import com.mementoguy.pulavey.util.Constants
import com.mementoguy.pulavey.util.LoadingDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RadioQuestionnaireFragment : Fragment() {

    private var _binding : FragmentRadioQuestionnaireBinding?= null
    private val binding get() = _binding!!
    private val surveyViewModel by sharedViewModel<SurveyViewModel>()
    private lateinit var loadingDialog : LoadingDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentRadioQuestionnaireBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argString= requireArguments().getString("questionnaire")
        val questionnaire= Gson().fromJson(argString, Questionnaire::class.java)
        loadingDialog= LoadingDialog(requireActivity())

        displayQuestion(questionnaire)
        setRadioButtonsLabel(questionnaire)

        when(questionnaire.question.next == null){
            true->  finishSurvey(questionnaire)
            false-> displayNextQuestion(questionnaire)
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

    private fun finishSurvey(questionnaire: Questionnaire){
        binding.btnNext.visibility= View.GONE
        binding.btnFinish.visibility= View.VISIBLE

        binding.btnFinish.setOnClickListener {
            val response= Response(questionId= questionnaire.question.id, value = getResponse(questionnaire.question))
            surveyViewModel.updateResponses(response)
            surveyViewModel.saveResponses()
            loadingDialog.startLoadingDialog()
            dismissLoader()
        }
    }

    private fun displayNextQuestion(questionnaire: Questionnaire) {
        binding.btnNext.setOnClickListener {
            val response= Response(questionId= questionnaire.question.id, value = getResponse(questionnaire.question))
            surveyViewModel.updateResponses(response)
            loadNextQuestion(questionnaire)
        }
    }

    private fun getResponse(question: Question): String {
        var responseString= ""
        with(binding){
            radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
                when(checkedId){
                    radioButton1.id -> responseString= question.options[0].value
                    radioButton2.id -> responseString=  question.options[1].value
                    radioButton3.id -> responseString= question.options[2].value
                }
            }
        }

        return responseString
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

    private fun dismissLoader(){
        Handler().postDelayed({
            loadingDialog.dismissDialog()
            Toast.makeText(requireContext(), "Responses saved successfully", Toast.LENGTH_SHORT).show()
        }, 1000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }

}