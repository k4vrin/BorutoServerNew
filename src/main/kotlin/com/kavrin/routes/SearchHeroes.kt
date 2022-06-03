package com.kavrin.routes

import com.kavrin.models.ApiResponse
import com.kavrin.repository.HeroRepository
import com.kavrin.util.Constants.NAME_KEY
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.searchHero() {

    val heroRepository: HeroRepository by inject()

    get(path = "/boruto/heroes/search") {
        val name = call.request.queryParameters[NAME_KEY]
        val apiResponse: ApiResponse = heroRepository.searchHeroes(name = name)

        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}