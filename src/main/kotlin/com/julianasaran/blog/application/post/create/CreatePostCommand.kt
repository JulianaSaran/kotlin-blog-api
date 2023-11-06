package com.julianasaran.blog.application.post.create

import com.julianasaran.blog.domain.author.Author

data class CreatePostCommand(
    val authorId: Author.Id,
    val title: String,
    val subtitle: String,
    val text: String,
) {
    data class Request(val title: String, val subtitle: String, val text: String) {
        fun command(authorId: Author.Id) = CreatePostCommand(
            authorId = authorId,
            title,
            subtitle,
            text,
        )
    }
}
