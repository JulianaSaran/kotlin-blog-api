package com.julianasaran.blog.domain.post

import com.julianasaran.blog.domain.author.Author
import com.julianasaran.blog.domain.exceptions.EntityNotFound
import java.time.LocalDateTime
import java.util.UUID

class Post(
    val id: Id,
    val authorId: Author.Id,
    val title: String,
    val subtitle: String,
    val text: String,
    val createdAt: LocalDateTime,
    val publishedAt: LocalDateTime?,
) {
    data class Id(val value: String)
    class NotFound : EntityNotFound("Post")

    companion object {
        fun create(authorId: Author.Id, title: String, subtitle: String, text: String) = Post(
            id = Id(UUID.randomUUID().toString()),
            authorId = authorId,
            title = title,
            subtitle = subtitle,
            text = text,
            createdAt = LocalDateTime.now(),
            publishedAt = null,
        )
    }
}
