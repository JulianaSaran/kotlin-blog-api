package com.julianasaran.blog.application.post.list

import com.julianasaran.blog.domain.post.Posts

class ListPostsQueryHandler(
    private val posts: Posts,
) {
    fun handler(): List<ListPostsQuery.Response> {
        val list = posts.listPosts()

        return list.map {
            ListPostsQuery.Response(
                id = it.id.value,
                authorId = it.authorId.value,
                title = it.title,
                subtitle = it.subtitle,
                text = it.text,
            )
        }
    }
}
