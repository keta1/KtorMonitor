package ro.cosminmihu.ktor.monitor.sample

import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put

private const val httpbin = "https://httpbin.org"

internal suspend fun makeCalls() {

    // HTML
    with(httpClient()) {
        runCatching { this.get(httpbin) }
        runCatching { this.delete(httpbin) }
        runCatching { this.patch(httpbin) }
        runCatching { this.post(httpbin) }
        runCatching { this.put(httpbin) }
    }

    // Images
    with(httpClient()) {
        runCatching { this.get("$httpbin/image") }
        runCatching { this.get("$httpbin/image/png") }
        runCatching { this.get("$httpbin/image/jpeg") }
        runCatching { this.get("$httpbin/image/svg") }
        runCatching { this.get("$httpbin/image/webp") }
    }

    // Response Content Type
    with(httpClient()) {
        runCatching { this.get("$httpbin/html") }
        runCatching { this.get("$httpbin/xml") }
        runCatching { this.get("$httpbin/json") }
        runCatching { this.get("$httpbin/robots.txt") }
        runCatching { this.get("$httpbin/gzip") }
        runCatching { this.get("$httpbin/encoding/utf8") }
        runCatching { this.get("$httpbin/deny") }
        runCatching { this.get("$httpbin/deflate") }
        runCatching { this.get("$httpbin/brotli") }
    }

    // Request Error
    with(httpClient()) {
        runCatching { this.get("https://-1-1") }
    }
}
