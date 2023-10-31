package com.julianasaran.blog.presenter.post

import com.julianasaran.blog.infrastructure.InMemoryAuthors
import com.julianasaran.blog.infrastructure.InMemoryPosts
import com.julianasaran.blog.presenter.http.configureRoutes
import config.TestingUuid
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CreatePostEndpointTest {
    private val authors = InMemoryAuthors()
    private val posts = InMemoryPosts()
    private val routes = configureRoutes(authors, posts)

    @Test
    fun `given the path when the id relates to a registered entity then return its id`() {
        TestingUuid.stack("post-xxx")
        val request = Request(Method.POST, "/authors/posts")
            .body(
                """
                    {
                        "title": "Lhama",
                        "subtitle": "subtitle-xxx",
                        "text": "Our Llama is a big fan of list items. When she spies a patch of them on a web page, she will eat them like sweets"
                    }
                """.trimIndent(),
            )

        val response = routes(request)

        Assertions.assertEquals(Status.CREATED, response.status)
        Assertions.assertEquals("""{"id":"post-xxx"}""", response.body.toString())
    }
}
