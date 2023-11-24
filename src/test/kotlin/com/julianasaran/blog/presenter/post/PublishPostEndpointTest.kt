package com.julianasaran.blog.presenter.post

import com.julianasaran.blog.infrastructure.InMemoryAuthors
import com.julianasaran.blog.infrastructure.InMemoryPosts
import com.julianasaran.blog.presenter.http.configureBlogRoutes
import config.spawner.PostSpawner
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PublishPostEndpointTest {
    private val authors = InMemoryAuthors()
    private val posts = InMemoryPosts()
    private val routes = configureBlogRoutes(authors, posts)

    init {
        posts.register(PostSpawner.lhama())
    }

    @Test
    fun `given the path when a post is published`() {
        val request = Request(Method.POST, "/posts/post-xxx/publish")
        val response = routes(request)

        Assertions.assertEquals(Status.NO_CONTENT, response.status)
        Assertions.assertEquals("""{"id":"post-xxx"}""", response.body.toString())
    }
}
