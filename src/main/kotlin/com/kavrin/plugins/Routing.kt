package com.kavrin.plugins

import com.kavrin.routes.getAllHeroes
import com.kavrin.routes.root
import com.kavrin.routes.searchHero
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {

    routing {
        // Routes have been defined in routes package
        root()
        getAllHeroes()
        searchHero()

        // To be able to access the images
        static("/images") {
            resources(resourcePackage = "images")
        }
    }
}
