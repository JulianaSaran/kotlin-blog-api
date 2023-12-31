package config

import org.junit.jupiter.api.Assertions
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Suppress("UNCHECKED_CAST")
fun <T : Any> asMap(value: T, type: KClass<T>): Map<String, Any?> {
    val props = type.memberProperties.associateBy { it.name }

    return props.keys.associateWith {
        when (val v = props[it]?.get(value)) {
            is String, is Int, is Float, is Boolean, null -> v
            is LocalDate, is LocalDateTime -> v.toString()
            is List<*> -> v.map { item -> asMap(item as Any, item::class as KClass<Any>) }
            else -> asMap(v, v::class as KClass<Any>)
        }
    }
}

inline fun <reified T : Any> T.asMap(): Map<String, Any?> {
    return asMap(this, T::class)
}

inline fun <reified T : Any> assertObjects(expected: T, actual: T) {
    Assertions.assertEquals(expected.asMap(), actual.asMap(), "objects are different")
}
