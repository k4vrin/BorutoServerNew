package com.kavrin.plugins

import com.kavrin.util.Constants.STATUS_PAGES_NOT_FOUND
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import javax.naming.AuthenticationException

fun Application.configureStatusPages() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { applicationCall, httpStatusCode ->
            applicationCall.respondText(
                text = STATUS_PAGES_NOT_FOUND,
                status = httpStatusCode
            )
        }
        exception<AuthenticationException> { call: ApplicationCall, cause ->
            call.respond(
                message = "We caught an exception. cause: $cause",
                status = HttpStatusCode.OK
            )
        }
    }
}