package com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.models

import java.io.Serializable

data class BooksResponseModel(
    val reading_log_entries: List<Entries>?,
    val page: Int?
) : Serializable

data class Entries(
    val work: Works,
    val logged_edition: String,
    val logged_date: String
) : Serializable

data class Works(
    val title: String,
    val key: String,
    val author_keys: List<String>,
    val author_names: List<String>,
    val first_publish_year: String,
    val lending_edition_s: String,
    val edition_key: List<String>,
    val cover_edition_key: String,
    val cover_id: Int
) : Serializable
