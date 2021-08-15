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
import com.mementoguy.pulavey.util.OnQuestionResponseListener

/**
 * Created by Edward Muturi on 22/04/2021.
 */
class QuestionnaireViewPagerAdapter(private val onQuestionResponseListener: OnQuestionResponseListener) : ListAdapter<Questionnaire, QuestionnaireViewPagerAdapter.ViewHolder>(itemDiffUtil) {
    private val listener= onQuestionResponseListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= LayoutQuestionnaireBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val layoutQuestionnaireBinding:  LayoutQuestionnaireBinding) : RecyclerView.ViewHolder(layoutQuestionnaireBinding.root) {

        fun bind(item: Questionnaire) {
            with(layoutQuestionnaireBinding) {
                val enMap= item.en.toMap()
                tvQuestion.text= enMap[item.question.questionText]
                btnNext.setOnClickListener {
                    listener.onQuestionResponse("Displaying Next Question")
                }

                if(item.question.questionType == "SELECT_ONE") {
                    radioGroup.visibility = View.VISIBLE
                    tilResponse.visibility= View.GONE

                    setRadioButtonsLabel(item)
                    handleRadioGroupSelection(item)
                } else {
                    tilResponse.visibility = View.VISIBLE
                    radioGroup.visibility = View.GONE
                }
            }

        }

        private fun setRadioButtonsLabel(questionnaire: Questionnaire){
            val enMap= questionnaire.en.toMap()
            with(layoutQuestionnaireBinding){
                radioButton1.text= enMap[questionnaire.question.options[0].displayText]
                radioButton2.text= enMap[questionnaire.question.options[1].displayText]
                radioButton3.text= enMap[questionnaire.question.options[2].displayText]
            }
        }

        private fun handleRadioGroupSelection(questionnaire: Questionnaire) {
            with(layoutQuestionnaireBinding){
                radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
                    val tag= QuestionnaireViewPagerAdapter::class.java.simpleName
                    when(checkedId){
                        radioButton1.id ->{ Log.e(tag, "selected Choice ${questionnaire.question.options[0].value}") }
                        radioButton2.id ->{ Log.e(tag, "selected Choice ${questionnaire.question.options[1].value}") }
                        radioButton3.id ->{ Log.e(tag, "selected Choice ${questionnaire.question.options[2].value}") }
                    }
                }
            }
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