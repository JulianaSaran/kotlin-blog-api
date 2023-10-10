package com.julianasaran.blog.domain.author

import com.julianasaran.blog.domain.exceptions.EntityNotFound
import java.time.LocalDateTime
import java.util.UUID

class Author(
    val id: Id,
    val name: String,
    val createdAt: LocalDateTime,
) {
    data class Id(val value: String)
    object NotFound : EntityNotFound("Author")
    companion object {
        fun create(name: String) = Author(
            id = Id(UUID.randomUUID().toString()),
            name = name,
            createdAt = LocalDateTime.now(),
        )
    }
}
