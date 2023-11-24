package com.julianasaran.blog.application.post

import com.julianasaran.blog.application.post.published.PublishPostCommand
import com.julianasaran.blog.application.post.published.PublishPostCommandHandler
import com.julianasaran.blog.domain.post.Post
import com.julianasaran.blog.infrastructure.InMemoryPosts
import config.spawner.PostSpawner
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PublishPostCommandHandlerTest {
    private val posts = InMemoryPosts()
    private val handler = PublishPostCommandHandler(posts)

    init {
        posts.register(PostSpawner.lhama())
    }

    private val response = PublishPostCommand.Response("post-xxx")

    @Test
    fun `given a command when published at is updated then return post id`() {
        val command = PublishPostCommand(Post.Id("post-xxx"))

        val entry = handler.handler(command)

        Assertions.assertEquals(entry, response)
    }
}
