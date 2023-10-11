package com.julianasaran.blog.presenter

import com.julianasaran.blog.infrastructure.InMemoryAuthors
import com.julianasaran.blog.presenter.http.configureRoutes
import config.TestingUuid
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RegisterAuthorEndpointTest {
    private val authors = InMemoryAuthors()
    private val routes = configureRoutes(authors)

    @Test
    fun `given the path when the id relates to a registered entity then return its id`() {
        TestingUuid.stack("author-xxx")
        val request = Request(Method.POST, "authors")
            .body(
                """
                    {
                        "name": "Arthur Dent"
                    }
                """.trimIndent(),
            )

        val response = routes(request)

        Assertions.assertEquals(Status.CREATED, response.status)
        Assertions.assertEquals("""{"id":"author-xxx"}""", response.body.toString())
    }
}
