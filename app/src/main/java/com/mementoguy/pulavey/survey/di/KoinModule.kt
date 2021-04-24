package com.mementoguy.pulavey.survey.di

import com.mementoguy.pulavey.survey.ui.SurveyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Edward Muturi on 24/04/2021.
 */

val appModule= module {
    viewModel { SurveyViewModel() }
}