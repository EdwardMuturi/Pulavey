package com.mementoguy.pulavey.survey.questionnaire

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mementoguy.pulavey.R
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_questionnare, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Question) {
            with(itemView) {
                // TODO: Bind the data with View
            }

            itemView.tag = this
            itemView.setOnClickListener(itemClickListener)
        }
    }
}

val itemDiffUtil = object : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
        TODO("not implemented")
    }

    override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
        TODO("not implemented")
    }
}