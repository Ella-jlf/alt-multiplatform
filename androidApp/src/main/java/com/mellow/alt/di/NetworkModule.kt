package com.mellow.alt.di

import com.mellow.alt.BuildConfig
import com.mellow.alt.data.repository.net.LoginRepositoryImpl
import com.mellow.alt.data.repository.net.service.LoginService
import com.mellow.alt.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    private val timeout: Long = 60L

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.server_url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient {

        var builder = OkHttpClient.Builder()
            /* .addNetworkInterceptor(Interceptor { chain ->
                 val requestBuilder = chain.request().newBuilder()
                 requestBuilder.addHeader("Content-Type", "application/json")
                 chain.proceed(requestBuilder.build())
             })*/
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            builder = builder.addNetworkInterceptor(interceptor)
        }

        return builder.build()
    }

    @Provides
    fun provideLogInService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Singleton
    @Provides
    fun provideLogInRepository(loginService: LoginService): LoginRepository {
        return LoginRepositoryImpl(loginService)
    }
}
