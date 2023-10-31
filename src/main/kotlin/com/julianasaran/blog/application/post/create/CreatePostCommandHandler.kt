package com.julianasaran.blog.application.post.create

import com.julianasaran.blog.domain.post.Post
import com.julianasaran.blog.domain.post.Posts

class CreatePostCommandHandler(
    private val posts: Posts,
) {
    fun handler(command: CreatePostCommand): PostDto {
        val post = Post.create(command.authorId, command.title, command.subtitle, command.text)

        posts.register(post)

        return PostDto(post.id.value)
    }
}
