package com.julianasaran.blog.infrastructure

import com.julianasaran.blog.domain.author.Author
import com.julianasaran.blog.domain.author.Authors

class InMemoryAuthors(
    private val items: MutableMap<String, Author> = mutableMapOf(),
) : Authors {
    override fun register(author: Author) {
        items[author.id.value] = author
    }

    override fun loadById(id: Author.Id): Author {
        return items[id.value] ?: throw Author.NotFound
    }
}
