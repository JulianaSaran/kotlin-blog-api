package config.spawner

import com.julianasaran.blog.domain.author.Author

class AuthorSpawner {
    companion object {
        fun arthurDent() = Author(
            id = Author.Id("author-xxx"),
            name = "Arthur Dent",
            createdAt = ClockSpawner.now(),
        )

        fun johnDoe() = Author(
            id = Author.Id("author-yyy"),
            name = "John Doe",
            createdAt = ClockSpawner.now(),
        )
    }
}
