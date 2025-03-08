package ro.cosminmihu.ktor.monitor.ui.formater

import androidx.compose.ui.text.AnnotatedString
import ro.cosminmihu.ktor.monitor.ui.model.ContentType
import ro.cosminmihu.ktor.monitor.ui.model.contentType

internal fun bodyHtml(
    contentType: String?,
    body: ByteArray?,
): AnnotatedString? {
    body ?: return null
    return when (contentType?.contentType) {
        ContentType.TEXT_HTML -> formatXml(body)
        else -> null
    }
}
