package com.mementoguy.pulavey.questionnaire

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mementoguy.pulavey.databinding.LayoutQuestionnareBinding
import com.mementoguy.pulavey.model.Question

/**
 * Created by Edward Muturi on 22/04/2021.
 */
class QuestionnareViewPagerAdapter() : ListAdapter<Question, QuestionnareViewPagerAdapter.ViewHolder>(itemDiffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= LayoutQuestionnareBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val layoutQuestionnareBinding:  LayoutQuestionnareBinding) : RecyclerView.ViewHolder(layoutQuestionnareBinding.root) {

        fun bind(item: Question) {
            with(layoutQuestionnareBinding) {
                tvQuestion.text= item.questionText

                if(item.questionType == "SELECT_ONE") {
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

        private fun setRadioButtonsLabel(question: Question){
            with(layoutQuestionnareBinding){
                radioButton1.text= question.options[0].displayText
                radioButton2.text= question.options[1].displayText
                radioButton3.text= question.options[2].displayText
            }
        }

        private fun handleRadioGroupSelection(question: Question) {
            with(layoutQuestionnareBinding){
                radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
                    val tag= QuestionnareViewPagerAdapter::class.java.simpleName
                    when(checkedId){
                        radioButton1.id ->{ Log.e(tag, "selected Choice ${question.options[0].value}") }
                        radioButton2.id ->{ Log.e(tag, "selected Choice ${question.options[1].value}") }
                        radioButton3.id ->{ Log.e(tag, "selected Choice ${question.options[2].value}") }
                    }
                }
            }
        }

    }
}

val itemDiffUtil = object : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem.id == newItem.id && oldItem.questionText == newItem.questionText
    }
}