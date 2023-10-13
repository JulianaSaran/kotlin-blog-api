package com.julianasaran.blog.domain.author

interface Authors {
    fun register(author: Author)
    fun loadById(id: Author.Id): Author
    fun findAuthors(name: String): List<Author>
}
