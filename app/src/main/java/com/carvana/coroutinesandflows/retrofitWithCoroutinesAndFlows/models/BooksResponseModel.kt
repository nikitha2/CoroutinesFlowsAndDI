package com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.models

import java.io.Serializable

data class BooksResponseModel(
    val reading_log_entries: List<Works>,
    val page:Int
) : Serializable


data class Works(
    val cover_id: String,
    val title: String,
    val author_names: List<String>,
    val author_keys: List<String>,
    val first_publish_year: String,
    val logged_edition: String,
    val logged_date:String
) : Serializable
