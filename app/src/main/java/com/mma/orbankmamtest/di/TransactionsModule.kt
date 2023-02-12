package com.mma.orbankmamtest.di

import com.mma.orbankmamtest.data.source.transaction.TransactionEndPoint
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TransactionRetrofit

@Module
@InstallIn(SingletonComponent::class)
object TransactionsModule {

    @TransactionRetrofit
    @Singleton
    @Provides
    fun provideTransactionRetrofit(transactionsUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(transactionsUrl)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()

    @Singleton
    @Provides
    fun provideTransactionEndpoint(retrofit: Retrofit): TransactionEndPoint =
        retrofit.create(TransactionEndPoint::class.java)
}