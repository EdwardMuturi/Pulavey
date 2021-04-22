package com.mementoguy.pulavey.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.mementoguy.pulavey.R
import com.mementoguy.pulavey.databinding.ActivitySurveyBinding
import com.mementoguy.pulavey.survey.questionnaire.QuestionnareViewPagerAdapter

private val questionnareViewPagerAdapter= QuestionnareViewPagerAdapter()
private lateinit var binding: ActivitySurveyBinding

class SurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vpSurvey.adapter= questionnareViewPagerAdapter
    }
}