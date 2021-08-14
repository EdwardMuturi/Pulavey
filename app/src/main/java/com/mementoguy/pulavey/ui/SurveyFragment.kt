package com.mementoguy.pulavey.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mementoguy.pulavey.databinding.FragmentSurveyBinding
import com.mementoguy.pulavey.model.Question
import com.mementoguy.pulavey.model.Response
import com.mementoguy.pulavey.questionnaire.QuestionnareViewPagerAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SurveyFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var mutableBinding: FragmentSurveyBinding? = null
    private val binding get() = mutableBinding!!

    private val questionnareViewPagerAdapter = QuestionnareViewPagerAdapter()
    private lateinit var loadingDialog: LoadingDialog
    private val surveyViewModel by sharedViewModel<SurveyViewModel>()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mutableBinding= FragmentSurveyBinding.inflate(inflater, container, false)
        loadingDialog= LoadingDialog(requireActivity())
        displayQuestions()
        return binding.root
    }

    private fun setUpQuestionnareViewPager(questions: List<Question>) {
        binding.vpSurvey.isUserInputEnabled= false
        binding.vpSurvey.adapter = questionnareViewPagerAdapter
        questionnareViewPagerAdapter.submitList(questions)
    }

    private fun displayQuestions() {
        surveyViewModel.questions.observe(viewLifecycleOwner, Observer { questions ->
            setUpQuestionnareViewPager(questions)
            displayNextQuestion(questions)
        })
    }

    private fun displayNextQuestion(questions: List<Question>) {
        binding.vpSurvey.currentItem++
    }


//    private fun finishSurvey(){
//        binding.btnFinish.setOnClickListener {
//            loadingDialog.startLoadingDialog()
//            saveResponses()
//            dismissLoader()
//        }
//    }

    private fun saveResponses(){
        val responses= listOf(Response(1,"surv1", "Qtn1", "yes"))
        surveyViewModel.saveResponses(responses)
    }

    private fun dismissLoader(){
        Handler().postDelayed({
            Toast.makeText(requireContext(), "Responses saved successfully", Toast.LENGTH_SHORT).show()
            binding.vpSurvey.currentItem= 0
            loadingDialog.dismissDialog()
        }, 1000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mutableBinding= null
    }
/*
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SurveyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}