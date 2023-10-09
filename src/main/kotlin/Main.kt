import com.julianasaran.blog.presenter.http.configureRoutes
import io.tcds.orm.connection.NestedTransactionConnection
import org.http4k.server.Netty
import org.http4k.server.asServer
import org.slf4j.LoggerFactory
import java.sql.DriverManager

fun main() {
//    val connection = NestedTransactionConnection(
//        readOnly = DriverManager.getConnection(System.getenv("JDBC_READ_URL")),
//        readWrite = DriverManager.getConnection(System.getenv("JDBC_WRITE_URL")),
//        logger = null, // LoggerFactory.getLogger("orm"),
//    )
//
//    val logger = LoggerFactory.getLogger("main")

    configureRoutes()
        .asServer(Netty(8080))
        .start()
}
