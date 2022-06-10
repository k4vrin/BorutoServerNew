package com.kavrin.models

@kotlinx.serialization.Serializable // Convert to JSON
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null, // for pagination
    val nextPage: Int? = null, // for pagination
    val heroes: List<Hero> = emptyList(),
    val lastUpdated: Long? = null
)
