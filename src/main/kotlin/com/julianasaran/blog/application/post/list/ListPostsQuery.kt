package com.julianasaran.blog.application.post.list

class ListPostsQuery() {
    data class Response(val id: String, val authorId: String, val title: String, val subtitle: String, val text: String)
}
