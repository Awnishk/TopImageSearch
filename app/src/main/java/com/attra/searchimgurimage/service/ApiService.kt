package com.attra.myimage.service

import com.attra.myimage.model.Gallery
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Attra Infotech...!
 * Created by Awnish Kumar on 15/09/2019.
 */

interface ApiService {
    @Headers("Authorization: Client-ID e0c9cbe113f0995")
    @GET("3/gallery/search/")

    fun getImageData(@Query("q") q: String): Deferred<Gallery>


    companion object {
        operator fun invoke(): ApiService {

            val okHttpClient = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.imgur.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

        }
    }
}

