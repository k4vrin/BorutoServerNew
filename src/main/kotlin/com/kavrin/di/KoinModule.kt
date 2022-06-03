package com.kavrin.di

import com.kavrin.repository.HeroRepository
import com.kavrin.repository.HeroRepositoryImpl
import org.koin.dsl.module

// App module
val koinModule = module {
    // Provide HeroRepository for getAllHeroes endpoint
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
}