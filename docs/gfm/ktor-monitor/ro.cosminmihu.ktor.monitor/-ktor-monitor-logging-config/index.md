//[ktor-monitor](../../../index.md)/[ro.cosminmihu.ktor.monitor](../index.md)/[KtorMonitorLoggingConfig](index.md)

# KtorMonitorLoggingConfig

[common]\
class [KtorMonitorLoggingConfig](index.md)

A configuration for the [KtorMonitorLogging](../-ktor-monitor-logging.md) plugin.

## Constructors

| | |
|---|---|
| [KtorMonitorLoggingConfig](-ktor-monitor-logging-config.md) | [common]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [isActive](is-active.md) | [common]<br>var [isActive](is-active.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Enable or disable the logging of requests and responses. By default: |
| [maxContentLength](max-content-length.md) | [common]<br>var [maxContentLength](max-content-length.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>The maximum length of the content that will be logged. After this, body will be truncated. By default it is [ContentLength.Default](../-content-length/-default.md). Use [ContentLength.Full](../-content-length/-full.md) to log the full content. |
| [retentionPeriod](retention-period.md) | [common]<br>var [retentionPeriod](retention-period.md): [Duration](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.time/-duration/index.html)<br>The retention period for the logs. By default it is 1 hour. |
| [showNotification](show-notification.md) | [common]<br>var [showNotification](show-notification.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Keep track of latest requests and responses into notification. *** Android only. By default it is enabled. |

## Functions

| Name | Summary |
|---|---|
| [filter](filter.md) | [common]<br>fun [filter](filter.md)(predicate: (HttpRequestBuilder) -&gt; [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Allows you to filter logs for calls matching a [predicate](filter.md). |
| [sanitizeHeader](sanitize-header.md) | [common]<br>fun [sanitizeHeader](sanitize-header.md)(placeholder: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) = &quot;***&quot;, predicate: ([String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Allows you to sanitize sensitive headers to avoid their values appearing in the logs. In the example below, Authorization header value will be replaced with '***' when logging: |