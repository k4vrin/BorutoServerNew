package com.kavrin.repository

import com.kavrin.models.ApiResponse
import com.kavrin.models.Hero

interface HeroRepository {

    suspend fun getAllHeroes(page: Int): ApiResponse

    suspend fun searchHeroes(name: String): ApiResponse

    val page1: List<Hero>
    val page2: List<Hero>
    val page3: List<Hero>
    val page4: List<Hero>
    val page5: List<Hero>

    // key and value
    val heroes: Map<Int, List<Hero>>
}