package com.mementoguy.pulavey

import android.app.Application
import com.mementoguy.pulavey.survey.di.apiModule
import com.mementoguy.pulavey.survey.di.appModule
import com.mementoguy.pulavey.survey.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Edward Muturi on 24/04/2021.
 */
class SurveyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SurveyApplication)
            modules(appModule, apiModule, networkModule)
        }
    }
}