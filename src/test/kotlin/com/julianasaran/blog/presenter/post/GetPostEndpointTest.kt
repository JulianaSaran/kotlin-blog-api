package com.julianasaran.blog.presenter.post

import com.julianasaran.blog.infrastructure.InMemoryAuthors
import com.julianasaran.blog.infrastructure.InMemoryPosts
import com.julianasaran.blog.presenter.http.configureBlogRoutes
import config.spawner.PostSpawner
import io.kotest.assertions.json.shouldMatchJson
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GetPostEndpointTest {
    private val posts = InMemoryPosts()
    private val authors = InMemoryAuthors()
    private val routes = configureBlogRoutes(authors, posts)

    init {
        posts.register(PostSpawner.lhama())
    }

    @Test
    fun `given the path when the there is a post id then return the post information`() {
        val request = Request(Method.GET, "/posts/post-xxx")

        val response = routes(request)

        Assertions.assertEquals(Status.OK, response.status)
        response.body.toString() shouldMatchJson """
        {
            "id": "post-xxx",
            "title": "Lhama",
            "subtitle": "subtitle-xxx",
            "text": "Our Llama is a big fan of list items. When she spies a patch of them on a web page, she will eat them like sweets"
        }
        """.trimIndent()
    }

    @Test
    fun `given the path when the there is no post id then return throw an exception`() {
        val request = Request(Method.GET, "/posts/yyy")

        val response = routes(request)

        Assertions.assertEquals(Status.NOT_FOUND, response.status)
        response.body.toString() shouldMatchJson """
        {
            "message": "Post not found"
        }
        """.trimIndent()
    }
}
