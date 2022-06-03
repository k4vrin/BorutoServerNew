package com.kavrin

import io.ktor.server.application.*
import com.kavrin.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureStatusPages()
}
