package com.mementoguy.pulavey.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.mementoguy.pulavey.R
import com.mementoguy.pulavey.survey.questionnaire.QuestionnareViewPagerAdapter

val questionnareViewPagerAdapter= QuestionnareViewPagerAdapter()

class SurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        val viewPager= findViewById<ViewPager2>(R.id.vp_survey)
        viewPager.adapter= questionnareViewPagerAdapter
    }
}