package com.kavrin.plugins

import com.kavrin.routes.root
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    routing {
        // Routes have been defined in routes package
        root()
    }
}
