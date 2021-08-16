package com.mementoguy.pulavey.questionnaire

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mementoguy.pulavey.databinding.LayoutQuestionnaireBinding
import com.mementoguy.pulavey.model.Questionnaire
import com.mementoguy.pulavey.model.toMap

/**
 * Created by Edward Muturi on 22/04/2021.
 */
class QuestionnaireViewPagerAdapter(val itemClick : (String) -> Unit) :
    ListAdapter<Questionnaire, QuestionnaireViewPagerAdapter.ViewHolder>(itemDiffUtil) {
//    private val listener = onQuestionResponseListener
    private val tag = QuestionnaireViewPagerAdapter::class.java.simpleName

    interface OnQuestionResponseListener {
        fun onQuestionResponse(response: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutQuestionnaireBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val layoutQuestionnaireBinding: LayoutQuestionnaireBinding) :
        RecyclerView.ViewHolder(layoutQuestionnaireBinding.root) {

        fun bind(item: Questionnaire) {
//            with(layoutQuestionnaireBinding) {
            val enMap = item.en.toMap()

            layoutQuestionnaireBinding.tvQuestion.text = enMap[item.question.questionText]

            var userResponse = ""
            when (item.question.questionType == "SELECT_ONE") {
                true -> {
                    layoutQuestionnaireBinding.radioGroup.visibility = View.VISIBLE
                    layoutQuestionnaireBinding.tilResponse.visibility = View.GONE

                    setRadioButtonsLabel(item)
                    userResponse= handleRadioGroupSelection(item)/*.also {
                        Log.e(tag, "radio: $it")
                    }*/
                }
                false -> {
                    layoutQuestionnaireBinding.tilResponse.visibility = View.VISIBLE
                    layoutQuestionnaireBinding.radioGroup.visibility = View.GONE
                    userResponse= layoutQuestionnaireBinding.tietResponse.text.toString().trim().also {
                        Log.e(tag, "resp: $it")
                    }
                }
            }

            /*layoutQuestionnaireBinding.btnNext.setOnClickListener {
                Log.e(tag, "bind: $userResponse")
                listener.onQuestionResponse(userResponse)
            }*/
            layoutQuestionnaireBinding.btnNext.setOnClickListener { itemClick(userResponse) }
//            }

        }

        private fun setRadioButtonsLabel(questionnaire: Questionnaire) {
            val enMap = questionnaire.en.toMap()
            with(layoutQuestionnaireBinding) {
                radioButton1.text = enMap[questionnaire.question.options[0].displayText]
                radioButton2.text = enMap[questionnaire.question.options[1].displayText]
                radioButton3.text = enMap[questionnaire.question.options[2].displayText]
            }
        }

        private fun handleRadioGroupSelection(questionnaire: Questionnaire): String {
            var selectedChoice = ""
            with(layoutQuestionnaireBinding) {
                radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
                    when (checkedId) {
                        radioButton1.id -> selectedChoice= questionnaire.question.options[0].value
                        radioButton2.id -> selectedChoice= questionnaire.question.options[1].value
                        radioButton3.id -> selectedChoice= questionnaire.question.options[2].value
//                        else -> String()
                    }
                }
            }
            Log.e(tag, "radioSelection: $selectedChoice")
            return selectedChoice
        }

    }
}

val itemDiffUtil = object : DiffUtil.ItemCallback<Questionnaire>() {
    override fun areItemsTheSame(oldItem: Questionnaire, newItem: Questionnaire): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Questionnaire, newItem: Questionnaire): Boolean {
        return oldItem.question.id == newItem.question.id && oldItem.question.questionText == newItem.question.questionText
    }
}