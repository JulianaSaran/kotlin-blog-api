package com.julianasaran.blog.application.post.get

import com.julianasaran.blog.domain.post.Posts

class GetPostQueryHandler(
    private val posts: Posts,
) {
    fun handler(query: GetPostQuery): GetPostQuery.Response {
        val post = posts.loadById(query.id)

        return GetPostQuery.Response(
            id = post.id.value,
            title = post.title,
            subtitle = post.subtitle,
            text = post.text,
        )
    }
}
