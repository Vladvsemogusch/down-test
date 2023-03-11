package cc.anisimov.vladyslav.downtest.di

import cc.anisimov.vladyslav.downtest.data.network.DownService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideUnsplashService(gsonConverterFactory: GsonConverterFactory): DownService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(DownService::class.java)
    }

    @Singleton
    @Provides
    fun provideGsonConvertFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    companion object {
        private const val BASE_URL = "https://run.mocky.io/"
    }
}