package com.example.swilehackathon.di

import com.example.swilehackathon.data.Repository
import com.example.swilehackathon.data.RepositoryImpl
import com.example.swilehackathon.network.SwileService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkInjection {

    private fun createRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return createRetrofit().build()
    }

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): SwileService.API {
        return retrofit.create(SwileService.API::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryInjection {

    @Binds
    fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository

}