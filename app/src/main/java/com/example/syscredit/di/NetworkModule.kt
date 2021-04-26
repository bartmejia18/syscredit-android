package com.example.syscredit.di

import com.example.syscredit.core.AppConstants.BASE_URL
import com.example.syscredit.data.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addNetworkInterceptor(interceptor)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideCreditsServices(retrofit: Retrofit): CreditsServices = retrofit.create(CreditsServices::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiCreditsHelper: ApiCreditsHelper): ApiCreditsHelper = apiCreditsHelper

    @Provides
    @Singleton
    fun provideLoginServices(retrofit: Retrofit): LoginServices = retrofit.create(LoginServices::class.java)

    @Provides
    @Singleton
    fun provideApiLoginHelper(apiLoginHelper: ApiLoginHelperImpl): ApiLoginHelper = apiLoginHelper

}