package ro.cosminmihu.ktor.monitor.library.ui.detail

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import ro.cosminmihu.ktor.monitor.library.domain.GetCallUseCase
import ro.cosminmihu.ktor.monitor.library.domain.model.ContentType
import ro.cosminmihu.ktor.monitor.library.domain.model.contentType
import ro.cosminmihu.ktor.monitor.library.domain.model.durationAsText
import ro.cosminmihu.ktor.monitor.library.domain.model.encodedPathAndQuery
import ro.cosminmihu.ktor.monitor.library.domain.model.host
import ro.cosminmihu.ktor.monitor.library.domain.model.isError
import ro.cosminmihu.ktor.monitor.library.domain.model.isInProgress
import ro.cosminmihu.ktor.monitor.library.domain.model.isSecure
import ro.cosminmihu.ktor.monitor.library.domain.model.requestDateTimeAsText
import ro.cosminmihu.ktor.monitor.library.domain.model.requestTimeAsText
import ro.cosminmihu.ktor.monitor.library.domain.model.responseDateTimeAsText
import ro.cosminmihu.ktor.monitor.library.domain.model.sizeAsText
import ro.cosminmihu.ktor.monitor.library.domain.model.totalSizeAsText
import ro.cosminmihu.ktor.monitor.library.ui.detail.DetailUiState.Call
import ro.cosminmihu.ktor.monitor.library.ui.detail.DetailUiState.Request
import ro.cosminmihu.ktor.monitor.library.ui.detail.DetailUiState.Response
import kotlin.time.Duration.Companion.seconds

private const val NO_DATA = "-"

class DetailViewModel(
    id: String,
    getCallUseCase: GetCallUseCase,
) : ViewModel() {

    val uiState = getCallUseCase(id)
        .map { call ->
            call ?: return@map DetailUiState()

            DetailUiState(
                summary = DetailUiState.Summary(
                    url = call.url,
                    method = call.method,
                    protocol = call.protocol ?: NO_DATA,
                    requestTime = call.requestDateTimeAsText,
                    responseCode = call.responseCode?.toString() ?: NO_DATA,
                    responseTime = call.responseDateTimeAsText ?: NO_DATA,
                    duration = call.durationAsText ?: NO_DATA,
                    requestSize = call.requestSize.sizeAsText(),
                    responseSize = call.responseSize?.sizeAsText() ?: NO_DATA,
                    totalSize = call.totalSizeAsText ?: NO_DATA,
                    isLoading = call.isInProgress,
                    isError = call.isError,
                ),
                call = Call(
                    id = call.id,
                    isSecure = call.isSecure,
                    request = Request(
                        method = call.method,
                        host = call.host,
                        pathAndQuery = call.encodedPathAndQuery,
                        requestTime = call.requestTimeAsText,
                        headers = call.requestHeaders,
                        body = DetailUiState.Body(
                            bytes = bodyBytes(call.requestBody),
                            raw = bodyRaw(call.responseContentType, call.requestBody),
                            code = bodyCode(call.requestContentType, call.requestBody),
                            image = bodyImage(call.requestContentType, call.requestBody),
                            html = bodyHtml(call.responseContentType, call.requestBody),
                        )
                    ),
                    response = Response(
                        responseCode = call.responseCode?.toString() ?: "",
                        contentType = call.responseContentType?.contentType ?: ContentType.UNKNOWN,
                        duration = call.durationAsText ?: "",
                        size = call.responseSize?.sizeAsText() ?: "",
                        error = call.error ?: "",
                        headers = call.responseHeaders ?: mapOf(),
                        body = DetailUiState.Body(
                            bytes = bodyBytes(call.responseBody),
                            raw = bodyRaw(call.responseContentType, call.responseBody),
                            code = bodyCode(call.responseContentType, call.responseBody),
                            image = bodyImage(call.responseContentType, call.responseBody),
                            html = bodyHtml(call.responseContentType, call.responseBody),
                        )
                    )
                )
            )
        }
        .flowOn(Dispatchers.Default)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            DetailUiState()
        )
}

private fun bodyBytes(body: ByteArray?): AnnotatedString? = body?.let {
    buildAnnotatedString {
        append(body.joinToString(" ") { byte -> byte.toString() })
    }
}

private fun bodyCode(contentType: String?, body: ByteArray?): AnnotatedString? =
    when (contentType?.contentType) {
        null -> null
        ContentType.TEXT_XML -> formatXml(body?.decodeToString() ?: "")
        ContentType.APPLICATION_XML -> formatXml(body?.decodeToString() ?: "")
        ContentType.APPLICATION_JSON -> formatJson(body?.decodeToString() ?: "")
        ContentType.TEXT_PLAIN -> null
        else -> null // TODO
    }

private fun formatTxt(txt: String): AnnotatedString = buildAnnotatedString {
    append(txt)
}

private fun formatXml(xml: String): AnnotatedString = buildAnnotatedString {
    try {
        val prettyXml = prettyXml(xml)

        val regex = Regex("""<[^>]+>|[^<\n]+|\n""") // Matches XML tags, content, and newlines

        regex.findAll(prettyXml).forEach { match ->
            val part = match.value
            when {
                part.startsWith("<") && part.endsWith(">") -> {
                    // XML Tags: Style them with blue color
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append(part)
                    }
                }

                part == "\n" -> {
                    // Keep the newline intact
                    append(part)
                }

                else -> {
                    // Text content outside tags.
                    append(part)
                }
            }
        }
    } catch (e: Exception) {
        // Handle errors in XML parsing
        withStyle(style = SpanStyle(color = Color.Red)) {
            append("Invalid XML: ${e.message}")
        }
    }
}

private fun prettyXml(xml: String): String {
    var indentLevel = 0
    val indentString = "    " // Indentation string (4 spaces)
    val builder = StringBuilder()

    // Process the XML string as a sequence of tags and text
    val regex = """(<[^>]+>|[^<]+)""".toRegex() // Match tags or text content

    regex.findAll(xml).forEach { matchResult ->
        val match = matchResult.value.trim()

        // If it's an opening tag, self-closing tag, or a closing tag
        when {
            match.startsWith("</") -> {
                indentLevel-- // Decrease indentation level for closing tags
                builder.append("\n")
                builder.append(indentString.repeat(indentLevel))
                builder.append(match)
            }

            match.startsWith("<") && match.endsWith("/>") -> {
                builder.append("\n")
                builder.append(indentString.repeat(indentLevel))
                builder.append(match)
            }

            match.startsWith("<") -> {
                builder.append("\n")
                builder.append(indentString.repeat(indentLevel))
                builder.append(match)
                indentLevel++ // Increase indentation level for opening tags
            }

            else -> {
                builder.append(match)
            }
        }
    }

    return builder.toString().trimIndent()
}

private fun formatJson(json: String): AnnotatedString = buildAnnotatedString {
    try {
        // Parse and pretty-print JSON
        val jsonElement = Json.parseToJsonElement(json)
        val prettyJson = Json { prettyPrint = true }
            .encodeToString(JsonElement.serializer(), jsonElement)

        // Regex to match JSON parts: strings, numbers, punctuation
        val regex = Regex("""("(\\.|[^"\\])*"|\d+|[{}\\[\\]:,]|\n|\s+)""")

        var isKey = true // Track if the current string is a key

        regex.findAll(prettyJson).forEach { match ->
            val part = match.value
            when {
                part.startsWith("\"") -> { // Keys or string values
                    if (isKey) {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF2E86C1), // Blue for keys

                            )
                        ) {
                            append(part)
                        }
                        isKey = false // Switch to value mode after key
                    } else {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF8E44AD), // Purple for string values

                            )
                        ) {
                            append(part)
                        }
                    }
                }

                part == ":" -> { // Colon separates keys and values
                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray, // Gray for punctuation

                        )
                    ) {
                        append(part)
                    }
                    isKey = false // Next string will be a value
                }

                part.matches(Regex("\\d+")) -> { // Numbers
                    withStyle(style = SpanStyle(color = Color(0xFF28BE69))) {
                        append(part)
                    }
                    isKey = true // Reset to key mode for the next string
                }

                part in "{}[]," -> { // Punctuation
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append(part)
                    }
                    isKey = true // Punctuation resets key tracking
                }

                part == "\n" || part == " " -> { // Preserve newlines and spaces
                    append(part)
                }

                else -> { // Fallback for unexpected cases
                    append(part)
                }
            }
        }
    } catch (_: Exception) {
        append("")
    }
}

private fun bodyImage(contentType: String?, body: ByteArray?) = when (contentType?.contentType) {
    null -> null
    ContentType.IMAGE_PNG -> body
    ContentType.IMAGE_JPEG -> body
    ContentType.IMAGE_WEBP -> body
    ContentType.IMAGE_GIF -> body // TODO android
    else -> null // TODO
}

private fun bodyHtml(contentType: String?, body: ByteArray?) = when (contentType?.contentType) {
    null -> null
    ContentType.TEXT_HTML -> formatTxt(body?.decodeToString() ?: "")
    else -> null // TODO
}

private fun bodyRaw(contentType: String?, body: ByteArray?) = buildAnnotatedString {
    append(body?.decodeToString())
}
