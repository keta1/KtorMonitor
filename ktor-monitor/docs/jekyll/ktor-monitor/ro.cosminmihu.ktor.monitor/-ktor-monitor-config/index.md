---
title: KtorMonitorConfig
---
//[ktor-monitor](../../../index.html)/[ro.cosminmihu.ktor.monitor](../index.html)/[KtorMonitorConfig](index.html)



# KtorMonitorConfig



[common]\
class [KtorMonitorConfig](index.html)

A configuration for the [KtorMonitorLogging](../-ktor-monitor-logging.html) plugin.



## Constructors


| | |
|---|---|
| [KtorMonitorConfig](-ktor-monitor-config.html) | [common]<br>constructor() |


## Properties


| Name | Summary |
|---|---|
| [isActive](is-active.html) | [common]<br>var [isActive](is-active.html): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Enable or disable the logging of requests and responses. Enabled by default. |
| [retentionPeriod](retention-period.html) | [common]<br>var [retentionPeriod](retention-period.html): [Duration](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.time/-duration/index.html)<br>The retention period for the logs. |
| [showNotification](show-notification.html) | [common]<br>var [showNotification](show-notification.html): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Keep track of latest requests and responses into notification. *** Android only. |


## Functions


| Name | Summary |
|---|---|
| [filter](filter.html) | [common]<br>fun [filter](filter.html)(predicate: (HttpRequestBuilder) -&gt; [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Allows you to filter log messages for calls matching a [predicate](filter.html). |
| [sanitizeHeader](sanitize-header.html) | [common]<br>fun [sanitizeHeader](sanitize-header.html)(placeholder: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) = &quot;***&quot;, predicate: ([String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Allows you to sanitize sensitive headers to avoid their values appearing in the logs. In the example below, Authorization header value will be replaced with '***' when logging: |
