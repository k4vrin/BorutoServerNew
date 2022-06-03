package com.kavrin.routes

import com.kavrin.models.ApiResponse
import com.kavrin.repository.HeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllHeroes() {
    // Inject HeroRepository
    val heroRepository: HeroRepository by inject()


    get(path = "/boruto/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            require(value = page in 1..5)

            val apiResponse: ApiResponse = heroRepository.getAllHeroes(page = page)

            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            // If user pass anything other than number
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = "Only Numbers Allowed."
                ),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            // If user pass a number that is not between 1 and 5
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = "Heroes not Found."
                ),
                status = HttpStatusCode.NotFound
            )
        }


    }
}