import com.julianasaran.blog.infrastructure.InMemoryAuthors
import com.julianasaran.blog.infrastructure.InMemoryPosts
import com.julianasaran.blog.presenter.http.configureAuthorRoutes
import org.http4k.server.Netty
import org.http4k.server.asServer

fun main() {
    val authors = InMemoryAuthors()
    val posts = InMemoryPosts()

    configureAuthorRoutes(authors, posts)
        .asServer(Netty(8080))
        .start()
}
