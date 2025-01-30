package ro.cosminmihu.ktor.monitor.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import ro.cosminmihu.ktor.monitor.KtorMonitorLogging
import ro.cosminmihu.ktor.monitor.KtorMonitorUI
import ro.cosminmihu.ktor.monitor.RetentionPeriod
import kotlin.time.Duration.Companion.seconds

@Composable
@Preview
fun App() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        Text(text = "Ktor Monitor Sample")
        KtorMonitorUI()
    }

    LaunchedEffect(Unit) {
        delay(2.seconds)
        listOf(
//            "https://png.pngtree.com/png-vector/20250117/ourlarge/pngtree-goldfish-ornamental-fish-creature-orange-cute-cartoon-three-dimensional-png-image_15181370.png",
//            "https://gsp.ro/", // TODO redirect
//            "https://media.istockphoto.com/id/1973365581/vector/sample-ink-rubber-stamp.jpg?s=612x612&w=0&k=20&c=_m6hNbFtLdulg3LK5LRjJiH6boCb_gcxPvRLytIz0Ws=",
            "https://dexonline.ro/cuvantul-zilei/json",
//            "hts://plm.xss/",
            "https://dexonline.ro/cuvantul-zilei/xml",
            "https://github.com/",
            "https://github.githubassets.com/assets/light-7aa84bb7e11e.css",
//            "ws://ws.mock",
//            "https://mp5a3efdb353ef904741.free.beeceptor.com/data"
        ).map {
            runCatching {
                httpClient().get(it)
            }
        }

//        runCatching {
//            delay(15.seconds)
//            httpClient().submitFormWithBinaryData(
//                url = "https://tests.free.beeceptor.com/get-api-token",
//                formData = formData {
//                    append("description", "Ktor logo")
//                }
//            )
//        }
    }
}

fun httpClient() = HttpClient {
    install(HttpTimeout) {
        connectTimeoutMillis = 20.seconds.inWholeMilliseconds
        requestTimeoutMillis = 20.seconds.inWholeMilliseconds
        socketTimeoutMillis = 20.seconds.inWholeMilliseconds
    }
    install(Logging) {
        level = LogLevel.ALL
        logger = Logger.SIMPLE
    }
    install(KtorMonitorLogging) {
        sanitizeHeader { header -> header == "Authorization" }
//        filter { request -> request.url.host.contains("github.com") }
        isActive = true
        showNotification = true
        retentionPeriod = RetentionPeriod.OneHour
    }
}