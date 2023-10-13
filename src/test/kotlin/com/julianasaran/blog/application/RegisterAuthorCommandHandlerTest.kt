package com.julianasaran.blog.application

import com.julianasaran.blog.application.register.RegisterAuthorCommand
import com.julianasaran.blog.application.register.RegisterAuthorCommandHandler
import com.julianasaran.blog.domain.author.Author
import com.julianasaran.blog.infrastructure.InMemoryAuthors
import config.TestingUuid
import config.assertObjects
import config.spawner.AuthorSpawner
import config.spawner.freezeClock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RegisterAuthorCommandHandlerTest {
    private val authors = InMemoryAuthors()
    private val handler = RegisterAuthorCommandHandler(authors)

    @Test
    fun `given a command then register the author`() = freezeClock {
        TestingUuid.stack("author-xxx")
        val command = RegisterAuthorCommand("Arthur Dent")

        val dto = handler.handler(command)

        val registered = authors.loadById(Author.Id(dto.id))
        assertObjects(AuthorSpawner.arthurDent(), registered)
        Assertions.assertEquals(AuthorDto("author-xxx"), dto)
    }
}
