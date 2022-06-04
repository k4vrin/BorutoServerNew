package com.kavrin.util

object TConstants {

    const val PREV_PAGE_KEY = "prevPage"
    const val NEXT_PAGE_KEY = "nextPage"
    const val SUCCESS_MESSAGE_OK = "OK"
    const val FAIL_MESSAGE_NUMBER = "Only Numbers Allowed."
    const val FAIL_MESSAGE_NOT_FOUND = "Heroes not Found."
    const val STATUS_PAGES_NOT_FOUND = "Page not Found."


    fun calcPage(page: Int): Map<String, Int?> {
        var prevPage: Int? = page
        var nextPage: Int? = page
        if (page in 1..4) nextPage = nextPage?.plus(1)
        if (page in 2..5) prevPage = prevPage?.minus(1)
        if (page == 5) nextPage = null
        if (page == 1) prevPage = null
        return mapOf(PREV_PAGE_KEY to prevPage, NEXT_PAGE_KEY to nextPage)
    }
}