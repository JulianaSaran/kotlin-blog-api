package com.julianasaran.blog.presenter.http

import com.julianasaran.blog.application.list.ListAuthorsQuery
import com.julianasaran.blog.application.list.ListAuthorsQueryHandler
import com.julianasaran.blog.application.register.RegisterAuthorCommand
import com.julianasaran.blog.application.register.RegisterAuthorCommandHandler
import com.julianasaran.blog.domain.author.Authors
import com.julianasaran.blog.presenter.http.plugins.get
import com.julianasaran.blog.presenter.http.plugins.json
import com.julianasaran.blog.presenter.http.plugins.post
import com.julianasaran.blog.presenter.http.plugins.receive
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.routes

fun configureRoutes(authors: Authors): RoutingHttpHandler {
    val register = RegisterAuthorCommandHandler(authors)
    val finder = ListAuthorsQueryHandler(authors)

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
    )
}
