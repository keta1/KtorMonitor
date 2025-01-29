package ro.cosminmihu.ktor.monitor.api

import kotlinx.datetime.Clock
import kotlin.random.Random

internal val callIdentifier
    get() = Clock.System.now().toEpochMilliseconds().toString() + "-" + Random.nextLong()