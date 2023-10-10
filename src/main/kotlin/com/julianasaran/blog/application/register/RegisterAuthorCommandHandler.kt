package com.julianasaran.blog.application.register

import com.julianasaran.blog.domain.author.Author
import com.julianasaran.blog.domain.author.Authors

class RegisterAuthorCommandHandler(private val authors: Authors) {
    fun handler(command: RegisterAuthorCommand): AuthorDto {
        val author = Author.create(command.name)

        authors.register(author)

        return AuthorDto(author.id.value)
    }
}
