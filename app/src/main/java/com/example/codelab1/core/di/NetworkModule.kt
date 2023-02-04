
package com.example.codelab1
import com.example.codelab1.BuildConfig
import com.example.codelab1.core.util.Constants
import com.example.codelab1.core.util.Constants.BASE_URL
import com.example.codelab1.data.ApiInterface
import com.example.codelab1.data.repositoriesImp.RepositoryImp
import com.example.codelab1.domain.repositories.RepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
//    const val BASE_URL = "https://www.panel2.ado-eg.com/api/"

    val interceptor: HttpLoggingInterceptor by lazy {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }


    val httpClient: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(false)
            .addInterceptor(interceptor)
            .followSslRedirects(false)
            .writeTimeout(60, TimeUnit.SECONDS)
    }

    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getMovieApi(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)




    @Provides
    fun provideRepository(apiInterface : ApiInterface):RepositoryInterface{
        return RepositoryImp(apiInterface)
    }

}