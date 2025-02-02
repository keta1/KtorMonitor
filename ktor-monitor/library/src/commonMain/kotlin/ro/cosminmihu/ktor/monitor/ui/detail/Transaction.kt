package ro.cosminmihu.ktor.monitor.ui.detail

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import io.ktor.utils.io.core.toByteArray
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ro.cosminmihu.ktor.monitor.ui.Dimens
import ro.cosminmihu.ktor.monitor.ui.LibraryTheme
import ro.cosminmihu.ktor.monitor.ui.components.Loading
import ro.cosminmihu.ktor.monitor.ui.resources.Res
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_error

@Composable
internal fun Transaction(
    isLoading: Boolean,
    isError: Boolean,
    headers: Map<String, List<String>>,
    body: DetailUiState.Body?,
    error: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(
            vertical = Dimens.Small,
            horizontal = Dimens.Medium
        ),
    ) {

        if (isLoading) {
            Loading.Medium()
            return
        }

        Headers(headers)

        if (isError) {
            Error(error)
            return
        }

        if (body.noBody) {
            Spacer(modifier = Modifier.padding(Dimens.Small))
            NoBody()
            return
        }

        Spacer(modifier = Modifier.padding(Dimens.Small))
        body?.let { Body(body = it) }
    }
}

@Composable
internal fun Error(error: String) {
    Icon(
        imageVector = Icons.Filled.Warning,
        contentDescription = stringResource(Res.string.ktor_error),
        tint = MaterialTheme.colorScheme.error,
    )
    SelectionContainer {
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(top = Dimens.Small),
        )
    }
}

@Composable
private fun Headers(headers: Map<String, List<String>>) {
    headers.forEach {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(it.key)
                }
                append(": ")
                append(it.value.joinToString(", "))
            },
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun TransactionPreview() {
    LibraryTheme {
        Transaction(
            isLoading = false,
            isError = false,
            headers = mapOf(
                "Content-Type" to listOf("text/html"),
                "Content-Length" to listOf("1234"),
            ),
            body = DetailUiState.Body(
                html = listOf(
                    AnnotatedString("<html>"),
                    AnnotatedString("<head>"),
                    AnnotatedString("<title>"),
                    AnnotatedString("Hello, World!"),
                    AnnotatedString("</title>"),
                    AnnotatedString("</head>"),
                    AnnotatedString("<body>"),
                    AnnotatedString("<h1>"),
                    AnnotatedString("Hello, World!"),
                    AnnotatedString("</h1>"),
                    AnnotatedString("</body>"),
                    AnnotatedString("</html>"),
                ),
                image = null,
                code = listOf(
                    AnnotatedString("fun main() {"),
                    AnnotatedString("    println(\"Hello, World!\")"),
                    AnnotatedString("}"),
                ),
                raw = AnnotatedString("Hello, World!"),
                bytes = AnnotatedString("Hello, World!".toByteArray().toString()),
            ),
            error = "Error message",
        )
    }
}
