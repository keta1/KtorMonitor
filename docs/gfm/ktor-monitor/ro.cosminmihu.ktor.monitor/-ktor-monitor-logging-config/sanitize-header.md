//[ktor-monitor](../../../index.md)/[ro.cosminmihu.ktor.monitor](../index.md)/[KtorMonitorLoggingConfig](index.md)/[sanitizeHeader](sanitize-header.md)

# sanitizeHeader

[common]\
fun [sanitizeHeader](sanitize-header.md)(placeholder: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) = &quot;***&quot;, predicate: ([String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))

Allows you to sanitize sensitive headers to avoid their values appearing in the logs. In the example below, Authorization header value will be replaced with '***' when logging:

```kotlin
sanitizeHeader { header -> header == HttpHeaders.Authorization }
```