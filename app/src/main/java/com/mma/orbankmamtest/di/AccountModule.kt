package com.mma.orbankmamtest.di

import com.mma.orbankmamtest.data.source.account.AccountEndPoint
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AccountModule {

    @Singleton
    @Provides
    fun provideAccountRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory()).build()))
        .build()

    @Singleton
    @Provides
    fun provideAccountEndpoint(retrofit: Retrofit): AccountEndPoint =
        retrofit.create(AccountEndPoint::class.java)

    companion object {
        const val BASE_URL = "https://run.mocky.io"
    }
}