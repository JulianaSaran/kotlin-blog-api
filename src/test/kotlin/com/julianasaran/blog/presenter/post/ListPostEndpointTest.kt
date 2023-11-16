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

class ListPostEndpointTest {
    private val authors = InMemoryAuthors()
    private val posts = InMemoryPosts()
    private val routes = configureBlogRoutes(authors, posts)

    init {
        posts.register(PostSpawner.lhama())
        posts.register(PostSpawner.snake())
        posts.register(PostSpawner.dog())
    }

    @Test
    fun `given the path when the publishedAt is not null entity return the posts`() {
        val request = Request(Method.GET, "/posts")

        val response = routes(request)

        Assertions.assertEquals(Status.OK, response.status)
        response.body.toString() shouldMatchJson """
            [
                {
                    "id": "post-xxx",
                    "authorId": "author-xxx",
                    "title": "Lhama",
                    "subtitle": "subtitle-xxx",
                    "text": "Our Llama is a big fan of list items. When she spies a patch of them on a web page, she will eat them like sweets"
                },
                {
                    "id": "post-yyy",
                    "authorId": "author-yyy",
                    "title": "Snake",
                    "subtitle": "subtitle-yyy",
                    "text": "The crafty anaconda likes to slither around the page, travelling rapidly by way of anchors to sneak up on his prey.."
                }
            ]
        """.trimIndent()
    }
}
