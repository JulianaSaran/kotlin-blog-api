package com.julianasaran.blog.presenter.author

import com.julianasaran.blog.infrastructure.InMemoryAuthors
import com.julianasaran.blog.infrastructure.InMemoryPosts
import com.julianasaran.blog.presenter.http.configureRoutes
import config.spawner.AuthorSpawner
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ListAuthorEndpointTest {
    private val authors = InMemoryAuthors()
    private val posts = InMemoryPosts()
    private val routes = configureRoutes(authors, posts)

    init {
        authors.register(AuthorSpawner.arthurDent())
        authors.register(AuthorSpawner.johnDoe())
    }

    @Test
    fun `given the path when the filter is empty entity then return all authors`() {
        val request = Request(Method.GET, "/authors")

        val response = routes(request)

        Assertions.assertEquals(Status.OK, response.status)
        Assertions.assertEquals("""[{"name":"Arthur Dent"},{"name":"John Doe"}]""", response.body.toString())
    }

    @Test
    fun `given the path when the query parameters relates to an author then return the list`() {
        val request = Request(Method.GET, "/authors?name=dent")

        val response = routes(request)

        Assertions.assertEquals(Status.OK, response.status)
        Assertions.assertEquals("""[{"name":"Arthur Dent"}]""", response.body.toString())
    }
}
