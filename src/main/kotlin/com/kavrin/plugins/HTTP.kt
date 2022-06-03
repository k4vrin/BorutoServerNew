package com.kavrin.plugins

import io.ktor.http.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.application.*
import java.time.Duration

fun Application.configureHTTP() {
    install(DefaultHeaders) {
        val oneYearInSeconds = Duration.ofDays(365).seconds
        // will send this header with each response
        // We want to cache the data for 1 year
        header(
            name = HttpHeaders.CacheControl,
            value = "public, max-age$oneYearInSeconds, immutable"
        )
    }
}
