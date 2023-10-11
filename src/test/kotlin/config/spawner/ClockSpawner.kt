package config.spawner

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import java.time.*

fun freezeClock(at: String = "2022-12-18T05:48:52Z") {
    mockkStatic(Clock::class)
    every { Clock.systemDefaultZone() } returns Clock.fixed(Instant.parse(at), ZoneOffset.UTC)
}

fun unfreezeClock() = unmockkStatic(Clock::class)

fun <T> freezeClock(at: String = "2022-12-18T05:48:52Z", block: () -> T): T {
    return freezeClock(at).let { block().apply { unfreezeClock() } }
}

class ClockSpawner {
    companion object {
        fun now(): LocalDateTime = LocalDateTime.of(2022, Month.DECEMBER, 18, 5, 48, 52)
    }
}
