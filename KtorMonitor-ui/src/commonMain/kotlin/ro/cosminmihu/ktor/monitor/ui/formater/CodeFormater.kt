package ro.cosminmihu.ktor.monitor.ui.formater

import androidx.compose.ui.text.AnnotatedString
import ro.cosminmihu.ktor.monitor.ui.model.ContentType
import ro.cosminmihu.ktor.monitor.ui.model.contentType

internal fun bodyCode(
    contentType: String?,
    body: ByteArray?,
): AnnotatedString? {
    body ?: return null
    return when (contentType?.contentType) {
        ContentType.TEXT_HTML,
        ContentType.TEXT_XML,
        ContentType.APPLICATION_XML,
        ContentType.IMAGE_SVG,
            -> formatXml(body)

        ContentType.APPLICATION_JSON,
        ContentType.APPLICATION_VND_API_JSON,
            -> formatJson(body)

        ContentType.TEXT_CSS,
            -> formatCSS(body)

        ContentType.TEXT_JAVASCRIPT,
        ContentType.APPLICATION_JAVASCRIPT,
            -> formatJavascript(body)

        ContentType.APPLICATION_FORM_URLENCODED,
            -> formatQueryString(body.decodeToString())

        else -> null
    }
}