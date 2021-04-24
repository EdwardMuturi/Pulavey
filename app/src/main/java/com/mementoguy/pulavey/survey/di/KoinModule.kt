package com.mementoguy.pulavey.survey.di

import com.mementoguy.pulavey.survey.data.SurveyRepository
import com.mementoguy.pulavey.survey.data.SurveyRepositoryImpl
import com.mementoguy.pulavey.survey.data.SurveyService
import com.mementoguy.pulavey.survey.ui.SurveyViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Edward Muturi on 24/04/2021.
 */

val appModule= module {
    single<SurveyRepository>{SurveyRepositoryImpl(get())}
    viewModel { SurveyViewModel(get()) }
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
        val base_url= "https://6049ea8cfb5dcc001796acdc.mockapi.io/"

        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single { provideRetrofit(get()) }
}