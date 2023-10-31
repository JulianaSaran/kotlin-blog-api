package com.julianasaran.blog.presenter.http

import com.julianasaran.blog.application.author.list.ListAuthorsQuery
import com.julianasaran.blog.application.author.list.ListAuthorsQueryHandler
import com.julianasaran.blog.application.author.register.RegisterAuthorCommand
import com.julianasaran.blog.application.author.register.RegisterAuthorCommandHandler
import com.julianasaran.blog.application.post.create.CreatePostCommand
import com.julianasaran.blog.application.post.create.CreatePostCommandHandler
import com.julianasaran.blog.domain.author.Authors
import com.julianasaran.blog.domain.post.Posts
import com.julianasaran.blog.presenter.extension.authorId
import com.julianasaran.blog.presenter.http.plugins.get
import com.julianasaran.blog.presenter.http.plugins.json
import com.julianasaran.blog.presenter.http.plugins.post
import com.julianasaran.blog.presenter.http.plugins.receive
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.routes

fun configureRoutes(authors: Authors, posts: Posts): RoutingHttpHandler {
    val register = RegisterAuthorCommandHandler(authors)
    val finder = ListAuthorsQueryHandler(authors)
    val creator = CreatePostCommandHandler(posts)

    return routes(
        post("/authors") { request ->
            val command = request.receive<RegisterAuthorCommand>()
            val response = register.handler(command)

            Response(Status.CREATED).json(response)
        },

        get("/authors") { request ->
            val query = ListAuthorsQuery(request.query("name") ?: "")
            val response = finder.handler(query)

            Response(Status.OK).json(response)
        },

        post("/authors/posts") { request ->
            val r = request.receive<CreatePostCommand.Resquest>()
            val command = r.command(authorId())
            val response = creator.handler(command)

            Response(Status.CREATED).json(response)
        },
    )
}
