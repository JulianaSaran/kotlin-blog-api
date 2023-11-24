package com.julianasaran.blog.application.post.published

import com.julianasaran.blog.domain.post.Posts

class PublishPostCommandHandler(
    private val posts: Posts,
) {
    fun handler(command: PublishPostCommand): PublishPostCommand.Response {
        val post = posts.loadById(command.postId)

        post.updatePublishedAt()

        posts.publish(post)

        return PublishPostCommand.Response(post.id.value)
    }
}
