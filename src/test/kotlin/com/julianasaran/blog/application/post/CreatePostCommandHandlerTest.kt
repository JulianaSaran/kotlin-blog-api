package com.julianasaran.blog.application.post

import com.julianasaran.blog.application.post.create.CreatePostCommand
import com.julianasaran.blog.application.post.create.CreatePostCommandHandler
import com.julianasaran.blog.application.post.create.PostDto
import com.julianasaran.blog.domain.author.Author
import com.julianasaran.blog.infrastructure.InMemoryPosts
import config.TestingUuid
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CreatePostCommandHandlerTest {
    private val posts = InMemoryPosts()
    private val handler = CreatePostCommandHandler(posts)

    @Test
    fun `given a command then register the author`() {
        TestingUuid.stack("post-xxx")
        val command = CreatePostCommand(
            authorId = Author.Id("author-xxx"),
            title = "Lhama",
            subtitle = "subtitle-xxx",
            text = "Our Llama is a big fan of list items. When she spies a patch of them on a web page, she will eat them like sweets",
        )

        val dto = handler.handler(command)

        Assertions.assertEquals(PostDto("post-xxx"), dto)
    }
}
