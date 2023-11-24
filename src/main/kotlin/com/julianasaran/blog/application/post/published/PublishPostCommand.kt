package com.julianasaran.blog.application.post.published

import com.julianasaran.blog.domain.post.Post

class PublishPostCommand(
    val postId: Post.Id,
) {
    data class Response(val id: String)
}
