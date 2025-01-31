//[ktor-monitor](../../../index.md)/[ro.cosminmihu.ktor.monitor](../index.md)/[KtorMonitorConfig](index.md)

# KtorMonitorConfig

[common]\
class [KtorMonitorConfig](index.md)

A configuration for the [KtorMonitorLogging](../-ktor-monitor-logging.md) plugin.

## Constructors

| | |
|---|---|
| [KtorMonitorConfig](-ktor-monitor-config.md) | [common]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [isActive](is-active.md) | [common]<br>var [isActive](is-active.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Enable or disable the logging of requests and responses. Enabled by default. |
| [retentionPeriod](retention-period.md) | [common]<br>var [retentionPeriod](retention-period.md): [Duration](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.time/-duration/index.html)<br>The retention period for the logs. |
| [showNotification](show-notification.md) | [common]<br>var [showNotification](show-notification.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Keep track of latest requests and responses into notification. *** Android only. |

## Functions

| Name | Summary |
|---|---|
| [filter](filter.md) | [common]<br>fun [filter](filter.md)(predicate: (HttpRequestBuilder) -&gt; [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Allows you to filter log messages for calls matching a [predicate](filter.md). |
| [sanitizeHeader](sanitize-header.md) | [common]<br>fun [sanitizeHeader](sanitize-header.md)(placeholder: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) = &quot;***&quot;, predicate: ([String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Allows you to sanitize sensitive headers to avoid their values appearing in the logs. In the example below, Authorization header value will be replaced with '***' when logging: |