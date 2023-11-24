package com.julianasaran.blog.domain

import config.spawner.ClockSpawner
import config.spawner.PostSpawner
import config.spawner.freezeClock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PostsTest {
    @Test
    fun `given a post when updatePublishedAt is called then update the date into the repository`() = freezeClock {
        val postNotPublished = PostSpawner.dog()

        assert(postNotPublished.publishedAt == null)

        postNotPublished.updatePublishedAt()

        Assertions.assertEquals(postNotPublished.publishedAt, ClockSpawner.now())
    }
}
