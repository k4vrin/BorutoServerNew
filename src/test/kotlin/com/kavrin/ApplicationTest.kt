package com.kavrin

import com.kavrin.models.ApiResponse
import com.kavrin.repository.HeroRepository
import com.kavrin.util.TConstants.FAIL_MESSAGE_NOT_FOUND
import com.kavrin.util.TConstants.FAIL_MESSAGE_NUMBER
import com.kavrin.util.TConstants.NEXT_PAGE_KEY
import com.kavrin.util.TConstants.PREV_PAGE_KEY
import com.kavrin.util.TConstants.SUCCESS_MESSAGE_OK
import com.kavrin.util.TConstants.calcPage
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import kotlin.test.*

class ApplicationTest : AutoCloseKoinTest() {

    private val heroRepository: HeroRepository by inject()

    /////////////////////// Root endpoint //////////////////////////////
    @Test
    fun `access root endpoint, assert correct information`() = testApplication {
        // Added automatically
//        application {
//            configureRouting()
//            module()
//        }
        client.get("/").apply {
            assertEquals(
                expected = HttpStatusCode.OK,
                actual = status
            )
            assertEquals(
                expected = "Welcome to Boruto API!",
                actual = bodyAsText()
            )
        }
    }
    /////////////////////// All heroes endpoint //////////////////////////////
    @Test
    fun `access all heroes endpoint, query not provided, assert correct information`() = testApplication {
        client.get(urlString = "/boruto/heroes").apply {
            assertEquals(
                expected = HttpStatusCode.OK,
                actual = status
            )
            val expected = ApiResponse(
                success = true,
                message = SUCCESS_MESSAGE_OK,
                prevPage = null,
                nextPage = 2,
                heroes = heroRepository.page1
            )

            val actual: ApiResponse = Json.decodeFromString(body())
            assertEquals(
                expected = expected,
                actual = actual
            )
        }
    }

    @Test
    fun `access all heroes endpoint, query all pages, assert correct information`() = testApplication {
        val pages = 1..5
        val heroes = listOf(
            heroRepository.page1,
            heroRepository.page2,
            heroRepository.page3,
            heroRepository.page4,
            heroRepository.page5,
        )

        pages.forEach { page ->
            client.get(urlString = "/boruto/heroes?page=$page").apply {

                assertEquals(
                    expected = HttpStatusCode.OK,
                    actual = status
                )


                val expected = ApiResponse(
                    success = true,
                    message = SUCCESS_MESSAGE_OK,
                    prevPage = calcPage(page = page)[PREV_PAGE_KEY],
                    nextPage = calcPage(page = page)[NEXT_PAGE_KEY],
                    heroes = heroes[page - 1]
                )
                val actual: ApiResponse = Json.decodeFromString(body())
                assertEquals(
                    expected = expected,
                    actual = actual
                )
            }
        }
    }

    @Test
    fun `access all heroes endpoint, query non existing page number, assert error`() = testApplication {
        client.get(urlString = "/boruto/heroes?page=6").apply {

            assertEquals(
                expected = HttpStatusCode.BadRequest,
                actual = status
            )


            val expected = ApiResponse(
                success = false,
                message = FAIL_MESSAGE_NOT_FOUND
            )
            val actual: ApiResponse = Json.decodeFromString(body())
            assertEquals(
                expected = expected,
                actual = actual
            )
        }
    }

    @Test
    fun `access all heroes endpoint, query invalid page number, assert error`() = testApplication {
        client.get(urlString = "/boruto/heroes?page=invalid").apply {

            assertEquals(
                expected = HttpStatusCode.BadRequest,
                actual = status
            )


            val expected = ApiResponse(
                success = false,
                message = FAIL_MESSAGE_NUMBER
            )
            val actual: ApiResponse = Json.decodeFromString(body())
            assertEquals(
                expected = expected,
                actual = actual
            )
        }
    }
}























