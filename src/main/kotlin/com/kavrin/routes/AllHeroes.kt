package com.kavrin.routes

import com.kavrin.models.ApiResponse
import com.kavrin.repository.HeroRepository
import com.kavrin.util.Constants.FAIL_MESSAGE_NOT_FOUND
import com.kavrin.util.Constants.FAIL_MESSAGE_NUMBER
import com.kavrin.util.Constants.PAGE_KEY
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
            val page = call.request.queryParameters[PAGE_KEY]?.toInt() ?: 1
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
                    message = FAIL_MESSAGE_NUMBER
                ),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            // If user pass a number that is not between 1 and 5
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = FAIL_MESSAGE_NOT_FOUND
                ),
                status = HttpStatusCode.BadRequest
            )
        }


    }
}