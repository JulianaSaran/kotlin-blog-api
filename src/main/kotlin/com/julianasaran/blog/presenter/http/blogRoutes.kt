package com.julianasaran.blog.presenter.http

import com.julianasaran.blog.application.author.list.ListAuthorsQuery
import com.julianasaran.blog.application.author.list.ListAuthorsQueryHandler
import com.julianasaran.blog.application.post.get.GetPostQuery
import com.julianasaran.blog.application.post.get.GetPostQueryHandler
import com.julianasaran.blog.application.post.list.ListPostsQueryHandler
import com.julianasaran.blog.application.post.published.PublishPostCommand
import com.julianasaran.blog.application.post.published.PublishPostCommandHandler
import com.julianasaran.blog.domain.author.Authors
import com.julianasaran.blog.domain.post.Post
import com.julianasaran.blog.domain.post.Posts
import com.julianasaran.blog.presenter.http.plugins.get
import com.julianasaran.blog.presenter.http.plugins.json
import com.julianasaran.blog.presenter.http.plugins.post
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.path
import org.http4k.routing.routes

fun Request.id() = path("postId")!!

fun configureBlogRoutes(authors: Authors, posts: Posts): RoutingHttpHandler {
    val finder = ListAuthorsQueryHandler(authors)
    val getter = GetPostQueryHandler(posts)
    val list = ListPostsQueryHandler(posts)
    val published = PublishPostCommandHandler(posts)

    return routes(
        get("/posts") {
            val response = list.handler()

            Response(Status.OK).json(response)
        },

        get("/posts/{postId}") { request ->
            val query = GetPostQuery(Post.Id(request.id()))
            val response = getter.handler(query)

            Response(Status.OK).json(response)
        },

        get("/authors/{authorId}/posts") {
            TODO("Not Yet Implemented")
        },

        get("/authors") { request ->
            val query = ListAuthorsQuery(request.query("name") ?: "")
            val response = finder.handler(query)

            Response(Status.OK).json(response)
        },

        get("/authors/{authorId}") {
            TODO("Not Yet Implemented")
        },

        post("/posts/{postId}/publish") { req ->
            val command = PublishPostCommand(Post.Id(req.id()))
            val response = published.handler(command)

            Response(Status.NO_CONTENT).json(response)
        },
    )
}
