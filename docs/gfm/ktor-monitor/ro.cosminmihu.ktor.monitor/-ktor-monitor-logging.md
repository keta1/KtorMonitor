//[ktor-monitor](../../index.md)/[ro.cosminmihu.ktor.monitor](index.md)/[KtorMonitorLogging](-ktor-monitor-logging.md)

# KtorMonitorLogging

[common]\
val [KtorMonitorLogging](-ktor-monitor-logging.md): ClientPlugin&lt;[KtorMonitorConfig](-ktor-monitor-config/index.md)&gt;

A [Ktor](https://ktor.io/) client plugin that provides the capability to log HTTP calls.

You can learn more from [KtorMonitor](https://github.com/CosminMihuMDC/KtorMonitor).

```kotlin
HttpClient {
   install(KtorMonitorLogging) {
      sanitizeHeader { header -> header == "Authorization" }
      filter { request -> !request.url.host.contains("cosminmihu.ro") }
      isActive = true
      showNotification = true
      retentionPeriod = RetentionPeriod.OneHour
      maxContentLength = ContentLength.Default
   }
}
```