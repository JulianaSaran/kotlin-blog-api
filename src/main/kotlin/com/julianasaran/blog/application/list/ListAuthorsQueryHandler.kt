package com.julianasaran.blog.application.list

import com.julianasaran.blog.domain.author.Authors

class ListAuthorsQueryHandler(
    private val authors: Authors,
) {
    fun handler(command: ListAuthorsQuery): List<ListAuthorsQuery.Response> {
        val list = authors.findAuthors(command.name)

        return list.map {
            ListAuthorsQuery.Response(
                name = it.name,
            )
        }
    }
}
