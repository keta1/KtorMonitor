---
title: KtorMonitorLogging
---
//[ktor-monitor](../../index.html)/[ro.cosminmihu.ktor.monitor](index.html)/[KtorMonitorLogging](-ktor-monitor-logging.html)



# KtorMonitorLogging



[common]\
val [KtorMonitorLogging](-ktor-monitor-logging.html): ClientPlugin&lt;[KtorMonitorLoggingConfig](-ktor-monitor-logging-config/index.html)&gt;



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


