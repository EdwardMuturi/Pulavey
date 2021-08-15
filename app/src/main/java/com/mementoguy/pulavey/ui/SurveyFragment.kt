package com.mementoguy.pulavey.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
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
import com.mementoguy.pulavey.util.OnQuestionResponseListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SurveyFragment : Fragment(), OnQuestionResponseListener {
    private var param1: String? = null
    private var param2: String? = null

    private var mutableBinding: FragmentSurveyBinding? = null
    private val binding get() = mutableBinding!!

    private val questionnareViewPagerAdapter = QuestionnareViewPagerAdapter(this)
    private lateinit var loadingDialog: LoadingDialog
    private val surveyViewModel by sharedViewModel<SurveyViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mutableBinding = FragmentSurveyBinding.inflate(inflater, container, false)
        loadingDialog = LoadingDialog(requireActivity())
        populateQuestionnaire()
        return binding.root
    }

    private fun setUpQuestionnareViewPager(questions: List<Question>) {
        binding.vpSurvey.isUserInputEnabled = false
        binding.vpSurvey.adapter = questionnareViewPagerAdapter
        questionnareViewPagerAdapter.submitList(questions)
    }

    private fun populateQuestionnaire() {
        surveyViewModel.questions.observe(viewLifecycleOwner, { questions ->
            setUpQuestionnareViewPager(questions)
        })
    }

    private fun displayNextQuestion() {
        binding.vpSurvey.currentItem++
    }

    override fun onQuestionResponse(response: Any) {
        Log.e(SurveyFragment::class.simpleName, "$response")
        displayNextQuestion()
    }

    private fun saveResponses() {
        val responses = listOf(Response(1, "surv1", "Qtn1", "yes"))
        surveyViewModel.saveResponses(responses)
    }

    private fun dismissLoader() {
        Handler().postDelayed({
            Toast.makeText(requireContext(), "Responses saved successfully", Toast.LENGTH_SHORT)
                .show()
            binding.vpSurvey.currentItem = 0
            loadingDialog.dismissDialog()
        }, 1000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mutableBinding = null
    }
}