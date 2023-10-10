package config

import io.mockk.every
import io.mockk.mockkStatic
import java.util.UUID

class TestingUuid {
    companion object {
        fun stack(vararg id: String) {
            val stack = id.toMutableList()

            mockkStatic(UUID::class)
            every { UUID.randomUUID().toString() } returnsMany stack
        }
    }
}
