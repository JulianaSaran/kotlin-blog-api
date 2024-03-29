package com.julianasaran.blog.infrastructure

import com.julianasaran.blog.domain.post.Post
import com.julianasaran.blog.domain.post.Posts

class InMemoryPosts(
    private val items: MutableMap<String, Post> = mutableMapOf(),
) : Posts {
    override fun register(post: Post) {
        items[post.id.value] = post
    }

    override fun loadById(id: Post.Id): Post {
        return items[id.value] ?: throw Post.NotFound()
    }

    override fun listPosts(): List<Post> {
        return items
            .values
            .filter { it.publishedAt != null }
            .toList()
    }

    override fun publish(post: Post) {
        items[post.id.value] = post
    }
}
