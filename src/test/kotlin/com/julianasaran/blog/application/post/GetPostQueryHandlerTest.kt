package com.julianasaran.blog.application.post

import com.julianasaran.blog.application.post.get.GetPostQuery
import com.julianasaran.blog.application.post.get.GetPostQueryHandler
import com.julianasaran.blog.domain.post.Post
import com.julianasaran.blog.infrastructure.InMemoryPosts
import config.spawner.PostSpawner
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GetPostQueryHandlerTest {
    private val posts = InMemoryPosts()
    private val handler = GetPostQueryHandler(posts)

    init {
        posts.register(PostSpawner.lhama())
    }

    private val response = GetPostQuery.Response(
        id = "post-xxx",
        title = "Lhama",
        subtitle = "subtitle-xxx",
        text = "Our Llama is a big fan of list items. When she spies a patch of them on a web page, she will eat them like sweets",
    )

    @Test
    fun `given a query when it is  by post id then list the authors`() {
        val query = GetPostQuery(Post.Id("post-xxx"))

        val entry = handler.handler(query)

        Assertions.assertEquals(entry, response)
    }
}
