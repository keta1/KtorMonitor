---
title: KtorMonitorLoggingConfig
---
//[ktor-monitor](../../../index.html)/[ro.cosminmihu.ktor.monitor](../index.html)/[KtorMonitorLoggingConfig](index.html)



# KtorMonitorLoggingConfig



[common]\
class [KtorMonitorLoggingConfig](index.html)

A configuration for the [KtorMonitorLogging](../-ktor-monitor-logging.html) plugin.



## Constructors


| | |
|---|---|
| [KtorMonitorLoggingConfig](-ktor-monitor-logging-config.html) | [common]<br>constructor() |


## Properties


| Name | Summary |
|---|---|
| [isActive](is-active.html) | [common]<br>var [isActive](is-active.html): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Enable or disable the logging of requests and responses. By default: |
| [maxContentLength](max-content-length.html) | [common]<br>var [maxContentLength](max-content-length.html): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>The maximum length of the content that will be logged. After this, body will be truncated. By default it is [ContentLength.Default](../-content-length/-default.html). Use [ContentLength.Full](../-content-length/-full.html) to log the full content. |
| [retentionPeriod](retention-period.html) | [common]<br>var [retentionPeriod](retention-period.html): [Duration](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.time/-duration/index.html)<br>The retention period for the logs. By default it is 1 hour. |
| [showNotification](show-notification.html) | [common]<br>var [showNotification](show-notification.html): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Keep track of latest requests and responses into notification. *** Android only. By default it is enabled. |


## Functions


| Name | Summary |
|---|---|
| [filter](filter.html) | [common]<br>fun [filter](filter.html)(predicate: (HttpRequestBuilder) -&gt; [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Allows you to filter logs for calls matching a [predicate](filter.html). |
| [sanitizeHeader](sanitize-header.html) | [common]<br>fun [sanitizeHeader](sanitize-header.html)(placeholder: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) = &quot;***&quot;, predicate: ([String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Allows you to sanitize sensitive headers to avoid their values appearing in the logs. In the example below, Authorization header value will be replaced with '***' when logging: |
