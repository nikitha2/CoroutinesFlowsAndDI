package com.carvana.coroutinesandflows.core.utils

import javax.inject.Inject

interface Environment {
    val baseUrl: String
    val buildVersion: String
}

class EnvironmentImpl @Inject constructor(): Environment {
    override val baseUrl: String = "https://openlibrary.org/people/mekBot/"
    override val buildVersion: String = ""
}