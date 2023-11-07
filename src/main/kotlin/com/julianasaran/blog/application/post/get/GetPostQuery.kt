package com.julianasaran.blog.application.post.get

import com.julianasaran.blog.domain.post.Post

class GetPostQuery(
    val id: Post.Id,
) {
    data class Response(val id: String, val title: String, val subtitle: String, val text: String)
}
