package com.julianasaran.blog.presenter.http

import com.julianasaran.blog.application.author.register.RegisterAuthorCommand
import com.julianasaran.blog.application.author.register.RegisterAuthorCommandHandler
import com.julianasaran.blog.application.post.create.CreatePostCommand
import com.julianasaran.blog.application.post.create.CreatePostCommandHandler
import com.julianasaran.blog.domain.author.Author
import com.julianasaran.blog.domain.author.Authors
import com.julianasaran.blog.domain.post.Posts
import com.julianasaran.blog.presenter.http.plugins.*
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.routes

fun authorizedAuthorId(): Author.Id {
    return Author.Id("author-xxx")
}

fun configureAuthorRoutes(authors: Authors, posts: Posts): RoutingHttpHandler {
    val register = RegisterAuthorCommandHandler(authors)
    val creator = CreatePostCommandHandler(posts)

    return routes(
        post("/authors") { request ->
            val command = request.receive<RegisterAuthorCommand>()
            val response = register.handler(command)

            Response(Status.CREATED).json(response)
        },

        get("/access-token") {
            TODO("Not Yet Implemented")
        },

        post("/posts") { req ->
            val request = req.receive<CreatePostCommand.Request>()
            val command = request.command(authorizedAuthorId())
            val response = creator.handler(command)

            Response(Status.CREATED).json(response)
        },

        put("/posts/{postId}") {
            TODO("Not Yet Implemented")
        },

        delete("/posts/{postId}") {
            TODO("Not Yet Implemented")
        },
    )
}
