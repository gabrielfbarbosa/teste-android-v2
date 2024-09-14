package br.com.gabriel.akinmovesp.api

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = "https://api.olhovivo.sptrans.com.br/v2.1/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    /*@Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        return OkHttpClient.Builder()
            .cookieJar(JavaNetCookieJar(cookieManager))
            .build()
    }*/

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideOlhoVivoApi(retrofit: Retrofit): OlhoVivoApi =
        retrofit.create(OlhoVivoApi::class.java)

}