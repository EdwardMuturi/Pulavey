package com.mementoguy.pulavey.di

import android.app.Application
import androidx.room.Room
import com.mementoguy.pulavey.data.SurveyRepository
import com.mementoguy.pulavey.data.SurveyRepositoryImpl
import com.mementoguy.pulavey.data.SurveyService
import com.mementoguy.pulavey.db.SurveyDao
import com.mementoguy.pulavey.db.SurveyDatabase
import com.mementoguy.pulavey.ui.SurveyViewModel
import com.mementoguy.pulavey.workers.SaveResponsesWorker
import com.mementoguy.pulavey.workers.SyncOfflineResponsesWorker
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Edward Muturi on 24/04/2021.
 */

val appModule= module {
    single<SurveyRepository>{SurveyRepositoryImpl(get(), get())}
    viewModel { SurveyViewModel(get(), get()) }
}

val apiModule= module {
    fun provideSurveyApi(retrofit: Retrofit) : SurveyService{
        return retrofit.create(SurveyService::class.java)
    }

    single { provideSurveyApi(get()) }
}

val networkModule = module {
    val connectTimeout : Long= 40
    val readTimeout : Long= 40

    fun provideHttpClient(): OkHttpClient{
        val okHttpClientBuilder= OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)

        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient) : Retrofit{
        val base_url= "https://run.mocky.io/"

        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single { provideRetrofit(get()) }
}

val dbModule = module {
    fun provideDatabase(application: Application): SurveyDatabase {
        return Room.databaseBuilder(application, SurveyDatabase::class.java, "survey")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideSurveyDao(surveyDatabase: SurveyDatabase) : SurveyDao {
        return surveyDatabase.surveyDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideSurveyDao(get()) }
}

val workerModule= module {
    worker {  SaveResponsesWorker(androidContext(), get()) }
    worker {  SyncOfflineResponsesWorker(androidContext(), get()) }
}