package ro.cosminmihu.ktor.monitor.ui.detail


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import ro.cosminmihu.ktor.monitor.domain.model.ContentType
import ro.cosminmihu.ktor.monitor.domain.model.contentType

internal fun bodyBytes(body: ByteArray?): AnnotatedString? = body?.let {
    buildAnnotatedString {
        append(body.joinToString(" ") { byte -> byte.toString() })
    }
}