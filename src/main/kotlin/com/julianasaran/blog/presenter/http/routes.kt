package com.julianasaran.blog.presenter.http

import com.julianasaran.blog.presenter.http.plugins.json
import com.julianasaran.blog.presenter.http.plugins.post
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.routes

fun configureRoutes(): RoutingHttpHandler {
    return routes(
        post("/xxx") { request ->

            Response(Status.ACCEPTED).json(listOf(""))
        },
    )
}
