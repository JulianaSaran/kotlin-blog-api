package com.julianasaran.blog.presenter.extension

import com.julianasaran.blog.domain.post.Post

fun postId(): Post.Id {
    return Post.Id("post-xxx")
}
