package com.julianasaran.blog.application

import com.julianasaran.blog.application.list.ListAuthorsQuery
import com.julianasaran.blog.application.list.ListAuthorsQueryHandler
import com.julianasaran.blog.infrastructure.InMemoryAuthors
import config.spawner.AuthorSpawner
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ListAuthorsQueryHandlerTest {
    private val authors = InMemoryAuthors()
    private val handler = ListAuthorsQueryHandler(authors)

    init {
        authors.register(AuthorSpawner.arthurDent())
    }

    private val response = ListAuthorsQuery.Response("Arthur Dent")

    @Test
    fun `given a query when it is filtered by name then list the authors`() {
        val query = ListAuthorsQuery(name = "Arthur Dent")

        val handler = handler.handler(query)

        Assertions.assertEquals(listOf(response), handler)
    }
}
