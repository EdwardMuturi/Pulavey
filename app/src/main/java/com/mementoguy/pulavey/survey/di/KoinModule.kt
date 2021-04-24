package com.mementoguy.pulavey.survey.di

import com.mementoguy.pulavey.survey.data.SurveyRepository
import com.mementoguy.pulavey.survey.data.SurveyRepositoryImpl
import com.mementoguy.pulavey.survey.data.SurveyService
import com.mementoguy.pulavey.survey.ui.SurveyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Edward Muturi on 24/04/2021.
 */

val appModule= module {
    single<SurveyRepository>{SurveyRepositoryImpl()}
    viewModel { SurveyViewModel(get()) }
}