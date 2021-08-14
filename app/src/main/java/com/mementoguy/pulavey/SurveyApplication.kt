package com.mementoguy.pulavey

import android.app.Application
import com.mementoguy.pulavey.di.*
import com.mementoguy.pulavey.util.SurveySharePref
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Edward Muturi on 24/04/2021.
 */
class SurveyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SurveySharePref.initialize(this)
        startKoin {
            androidLogger()
            androidContext(this@SurveyApplication)
            workManagerFactory()
            modules(appModule, apiModule, networkModule, dbModule, workerModule)
        }
    }
}