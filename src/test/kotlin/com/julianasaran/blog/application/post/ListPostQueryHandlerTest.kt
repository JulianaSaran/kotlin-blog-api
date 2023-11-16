package com.julianasaran.blog.application.post

import com.julianasaran.blog.application.post.list.ListPostsQuery
import com.julianasaran.blog.application.post.list.ListPostsQueryHandler
import com.julianasaran.blog.infrastructure.InMemoryPosts
import config.spawner.PostSpawner
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ListPostQueryHandlerTest {
    private val posts = InMemoryPosts()
    private val handler = ListPostsQueryHandler(posts)

    init {
        posts.register(PostSpawner.lhama())
        posts.register(PostSpawner.snake())
    }

    private val list = listOf(
        ListPostsQuery.Response(
            id = "post-xxx",
            authorId = "author-xxx",
            title = "Lhama",
            subtitle = "subtitle-xxx",
            text = "Our Llama is a big fan of list items. When she spies a patch of them on a web page, she will eat them like sweets",
        ),
        ListPostsQuery.Response(
            id = "post-yyy",
            authorId = "author-yyy",
            title = "Snake",
            subtitle = "subtitle-yyy",
            text = "The crafty anaconda likes to slither around the page, travelling rapidly by way of anchors to sneak up on his prey..",
        ),
    )

    @Test
    fun `given a query then list the posts`() {
        val entry = handler.handler()

        Assertions.assertEquals(entry, list)
    }
}
