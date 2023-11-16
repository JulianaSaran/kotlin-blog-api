package com.julianasaran.blog.domain.post

interface Posts {
    fun register(post: Post)
    fun loadById(id: Post.Id): Post
    fun listPosts(): List<Post>
}
