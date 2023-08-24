package com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer

import com.carvana.coroutinesandflows.retroPhoto.repository.GetDataService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstanceForBooks {
    private const val BASE_URL = "https://openlibrary.org/people/mekBot"

    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var getDataService: BooksService = retrofit.create(BooksService::class.java)

}