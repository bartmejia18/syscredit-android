package com.example.syscredit.di

import com.example.syscredit.core.AppConstants.BASE_URL
import com.example.syscredit.data.api.*
import com.example.syscredit.data.services.CreditsServices
import com.example.syscredit.data.services.LoginServices
import com.example.syscredit.data.services.PaymentServices
import com.example.syscredit.data.services.PermissionServices
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
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
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
    fun provideCreditsServices(retrofit: Retrofit): CreditsServices = retrofit.create(CreditsServices::class.java)

    @Provides
    fun provideApiHelper(apiCreditsHelperImpl: ApiCreditsHelperImpl): ApiCreditsHelper = apiCreditsHelperImpl

    @Provides
    fun provideLoginServices(retrofit: Retrofit): LoginServices = retrofit.create(LoginServices::class.java)

    @Provides
    fun provideApiLoginHelper(apiLoginHelperImpl: ApiLoginHelperImpl): ApiLoginHelper = apiLoginHelperImpl

    @Provides
    fun providePaymentServices(retrofit: Retrofit): PaymentServices = retrofit.create(PaymentServices::class.java)

    @Provides
    fun provideApiPaymentHelper(apiPaymentHelperImpl: ApiPaymentHelperImpl): ApiPaymentHelper = apiPaymentHelperImpl

    @Provides
    fun providePermissionServices(retrofit: Retrofit): PermissionServices = retrofit.create(PermissionServices::class.java)

    @Provides
    fun provideApiPermissionHelper(apiPermissionHelperImpl: ApiPermissionHelperImpl): ApiPermissionHelper = apiPermissionHelperImpl
}