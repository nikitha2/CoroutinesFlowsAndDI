package com.carvana.coroutinesandflows

import com.carvana.coroutinesandflows.retroPhoto.repository.GetDataService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var getDataService: GetDataService = retrofit.create(GetDataService::class.java)

}