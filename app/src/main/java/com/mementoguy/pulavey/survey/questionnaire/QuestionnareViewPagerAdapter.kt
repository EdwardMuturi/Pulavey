package com.mementoguy.pulavey.survey.questionnaire

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mementoguy.pulavey.databinding.LayoutQuestionnareBinding
import com.mementoguy.pulavey.survey.model.Question

/**
 * Created by Edward Muturi on 22/04/2021.
 */
class QuestionnareViewPagerAdapter() : ListAdapter<Question, QuestionnareViewPagerAdapter.ViewHolder>(itemDiffUtil) {

    private var mutableItemClickListener: View.OnClickListener? = null
    private val itemClickListener: View.OnClickListener by lazy {
        mutableItemClickListener!!
    }

    fun setOnItemClickListener(itemClickListener: View.OnClickListener) {
        mutableItemClickListener = itemClickListener
    }

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
            }

            itemView.tag = this
            itemView.setOnClickListener(itemClickListener)
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