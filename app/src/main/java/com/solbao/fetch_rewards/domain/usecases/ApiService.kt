package com.solbao.fetch_rewards.domain.usecases

import com.solbao.fetch_rewards.data.models.ResponseItem
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET


interface ApiService {

        @GET("/hiring.json")
        fun list(): Call<List<ResponseItem>>
    companion object {
       private val contentType = "application/json".toMediaType()
       private val retrofit = Retrofit.Builder()
            .baseUrl("https://hiring.fetch.com")
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
        val  instance: ApiService? by lazy {
            retrofit.create(ApiService::class.java)
        }
    }
}