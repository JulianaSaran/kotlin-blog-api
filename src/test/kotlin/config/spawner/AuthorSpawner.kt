package config.spawner

import com.julianasaran.blog.domain.author.Author

class AuthorSpawner {
    companion object {
        fun arthurDent() = Author(
            id = Author.Id("author-xxx"),
            name = "Arthur Dent",
            createdAt = ClockSpawner.now(),
        )
    }
}
