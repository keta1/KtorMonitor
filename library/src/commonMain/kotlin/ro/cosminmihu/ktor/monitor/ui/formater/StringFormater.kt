package ro.cosminmihu.ktor.monitor.ui.formater

import androidx.compose.ui.text.buildAnnotatedString

internal fun bodyString(body: ByteArray?) = buildAnnotatedString {
    append(body?.decodeToString())
}