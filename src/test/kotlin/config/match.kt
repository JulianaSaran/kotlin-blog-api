package config

import io.mockk.MockKMatcherScope
import io.tcds.orm.Param
import io.tcds.orm.statement.Statement
import org.junit.jupiter.api.Assertions

fun MockKMatcherScope.matchQuery(expected: () -> String): Statement = matchNullable {
    Assertions.assertEquals(expected(), it?.toSql(), "matchQuery failed")

    true
}

fun MockKMatcherScope.matchParams(expected: () -> List<Pair<String, Any>>): List<Param<String, Any>> = matchNullable {
    Assertions.assertEquals(expected(), it?.map { p -> Pair(p.column.name, p.value) }, "matchParams failed")

    true
}
