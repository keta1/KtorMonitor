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

internal fun bodyString(body: ByteArray?) = buildAnnotatedString {
    append(body?.decodeToString())
}

internal fun bodyImage(contentType: String?, body: ByteArray?) = when (contentType?.contentType) {
    null -> null
    ContentType.IMAGE_PNG -> body
    ContentType.IMAGE_JPEG -> body
    ContentType.IMAGE_WEBP -> body
    ContentType.IMAGE_GIF -> body // TODO android
    else -> null // TODO
}

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
            -> formatText(body)

        ContentType.TEXT_CSS,
            -> formatCSS(body)

        else -> null
    }
}

internal fun bodyHtml(
    contentType: String?,
    body: ByteArray?,
): AnnotatedString? {
    body ?: return null
    return when (contentType?.contentType) {
        ContentType.TEXT_HTML,
            -> formatXml(body)

        else -> null
    }
}

private fun formatText(txt: ByteArray): AnnotatedString = bodyString(txt)

private fun formatJson(json: ByteArray): AnnotatedString = buildAnnotatedString {
    try {
        // Parse and pretty-print JSON
        val jsonElement = Json.parseToJsonElement(json.decodeToString())
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

private fun formatXml(xml: ByteArray): AnnotatedString = buildAnnotatedString {
    try {
        val prettyXml = prettyXml(xml.decodeToString())

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

private fun formatCSS(css: ByteArray): AnnotatedString {
    val propertyColor = Color.Blue
    val valueColor = Color(0xFF007700)
    val symbolColor = Color.Gray

    return buildAnnotatedString {
        val regex = Regex("""([\w\-]+):\s*([^;]+);|([{}])""")
        var lastIndex = 0

        val input = css.decodeToString()
        for (match in regex.findAll(input)) {
            append(input.substring(lastIndex, match.range.first))
            lastIndex = match.range.last + 1

            match.groups[1]?.let { property ->
                withStyle(style = SpanStyle(color = propertyColor, fontWeight = FontWeight.Bold)) {
                    append(property.value)
                }
                append(": ")
            }
            match.groups[2]?.let { value ->
                withStyle(style = SpanStyle(color = valueColor)) {
                    append(value.value)
                }
                append(";")
            }
            match.groups[3]?.let { symbol ->
                withStyle(style = SpanStyle(color = symbolColor)) {
                    append(symbol.value)
                }
            }
        }
        append(input.substring(lastIndex))
    }
}
