//[ktor-monitor](../../../index.md)/[ro.cosminmihu.ktor.monitor](../index.md)/[KtorMonitorLoggingConfig](index.md)/[maxContentLength](max-content-length.md)

# maxContentLength

[common]\
var [maxContentLength](max-content-length.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)

The maximum length of the content that will be logged. After this, body will be truncated. By default it is [ContentLength.Default](../-content-length/-default.md). Use [ContentLength.Full](../-content-length/-full.md) to log the full content.