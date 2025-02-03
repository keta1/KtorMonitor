package ro.cosminmihu.ktor.monitor.core

internal actual object PlatformConfig {

    actual val isDebug: Boolean =
        System.getProperty("debug")?.toBoolean() == true ||
            System.getProperty("app.debug")?.toBoolean() == true ||
            java.lang.management.ManagementFactory.getRuntimeMXBean()
                .inputArguments.any { it.contains("-agentlib:jdwp") }
}