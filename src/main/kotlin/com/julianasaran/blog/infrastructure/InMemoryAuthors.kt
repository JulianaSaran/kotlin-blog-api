package com.julianasaran.blog.infrastructure

import com.julianasaran.blog.domain.author.Author
import com.julianasaran.blog.domain.author.Authors
import java.util.Locale

class InMemoryAuthors(
    private val items: MutableMap<String, Author> = mutableMapOf(),
) : Authors {
    override fun register(author: Author) {
        items[author.id.value] = author
    }

    override fun loadById(id: Author.Id): Author {
        return items[id.value] ?: throw Author.NotFound
    }

    override fun findAuthors(name: String): List<Author> {
        return items
            .values
            .filter {
                it.name.lowercase(Locale.getDefault())
                    .contains(name.lowercase())
            }
            .toList()
    }
}
