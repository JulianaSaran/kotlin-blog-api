package config.spawner

import com.julianasaran.blog.domain.author.Author
import com.julianasaran.blog.domain.post.Post

class PostSpawner {
    companion object {
        fun lhama() = Post(
            id = Post.Id("post-xxx"),
            authorId = Author.Id("author-xxx"),
            title = "Lhama",
            subtitle = "subtitle-xxx",
            text = "Our Llama is a big fan of list items. When she spies a patch of them on a web page, she will eat them like sweets",
            createdAt = ClockSpawner.now(),
            publishedAt = ClockSpawner.now(),
        )

        fun snake() = Post(
            id = Post.Id("post-yyy"),
            authorId = Author.Id("author-yyy"),
            title = "Snake",
            subtitle = "subtitle-yyy",
            text = "The crafty anaconda likes to slither around the page, travelling rapidly by way of anchors to sneak up on his prey..",
            createdAt = ClockSpawner.now(),
            publishedAt = ClockSpawner.now(),
        )

        fun dog() = Post(
            id = Post.Id("post-zzz"),
            authorId = Author.Id("author-xxx"),
            title = "Dog",
            subtitle = "subtitle-zzz",
            text = "The dog is the best friend of the men",
            createdAt = ClockSpawner.now(),
            publishedAt = null,
        )
    }
}
