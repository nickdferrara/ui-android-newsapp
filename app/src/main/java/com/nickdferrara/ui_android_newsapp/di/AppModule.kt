package com.nickdferrara.ui_android_newsapp.di

import com.nickdferrara.ui_android_newsapp.data.remote.NewYorkTimesApi
import com.nickdferrara.ui_android_newsapp.repository.ArticleRepository
import com.nickdferrara.ui_android_newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        api: NewYorkTimesApi
    ) = ArticleRepository(api)

    @Singleton
    @Provides
    fun provideNewYorkTimesApi(): NewYorkTimesApi {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(NewYorkTimesApi::class.java)
    }
}