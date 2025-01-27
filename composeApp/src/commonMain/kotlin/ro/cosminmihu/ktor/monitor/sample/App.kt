package ro.cosminmihu.ktor.monitor.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import ro.cosminmihu.ktor.monitor.library.ui.KtorMonitorUI
import ro.cosminmihu.ktor.monitor.sample.theme.SampleTheme
import kotlin.time.Duration.Companion.seconds

@Composable
@Preview
fun App() {
    // TODO mock
    LaunchedEffect(Unit) {
//        delay(2.seconds)
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

        runCatching {
            delay(15.seconds)
            httpClient().submitFormWithBinaryData(
                url = "https://tests.free.beeceptor.com/get-api-token",
                formData = formData {
                    append("description", "Ktor logo")
                }
            )
        }
    }

    SampleTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            KtorMonitorUI(
                modifier = Modifier.weight(1f),
                useLibraryTheme = false
            )
        }
    }
}