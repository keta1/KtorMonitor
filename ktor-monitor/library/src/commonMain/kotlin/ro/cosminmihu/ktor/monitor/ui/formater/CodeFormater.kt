package ro.cosminmihu.ktor.monitor.ui.formater

import androidx.compose.ui.text.AnnotatedString
import ro.cosminmihu.ktor.monitor.domain.model.ContentType
import ro.cosminmihu.ktor.monitor.domain.model.contentType
import ro.cosminmihu.ktor.monitor.ui.detail.bodyString

internal fun bodyCode(
    contentType: String?,
    body: ByteArray?,
): AnnotatedString? {
    body ?: return null
    return when (contentType?.contentType) {
        ContentType.TEXT_XML,
        ContentType.APPLICATION_XML,
        ContentType.TEXT_HTML,
            -> formatXml(body)

        ContentType.APPLICATION_JSON,
            -> formatJson(body)

        ContentType.TEXT_PLAIN,
            -> bodyString(body)

        ContentType.TEXT_CSS,
            -> formatCSS(body)

        else -> null
    }
}