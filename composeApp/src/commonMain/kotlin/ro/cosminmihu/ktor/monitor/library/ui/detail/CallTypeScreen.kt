package ro.cosminmihu.ktor.monitor.library.ui.detail

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
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.Res
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.error
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.no_body
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.response_view_binary
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.response_view_code
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.response_view_html
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.response_view_image
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.response_view_raw
import ro.cosminmihu.ktor.monitor.library.ui.components.Loading

private const val SHOW_TYPE_HTML = 0
private const val SHOW_TIME_IMAGE = 1
private const val SHOW_TYPE_CODE = 2
private const val SHOW_TYPE_RAW = 3
private const val SHOW_TYPE_BYTES = 4

@Composable
fun CallDetailsScreen(
    isLoading: Boolean,
    isError: Boolean,
    headers: Map<String, List<String>>,
    body: DetailUiState.Body?,
    error: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp),
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
            Spacer(modifier = Modifier.padding(8.dp))
            NoBody()
            return
        }

        Spacer(modifier = Modifier.padding(8.dp))
        body?.let { Body(body = it) }
    }
}

@Composable
private fun Body(body: DetailUiState.Body, modifier: Modifier = Modifier) {
    var selectedDisplayMode by remember(body) {
        mutableIntStateOf(
            when {
                body.html != null -> SHOW_TYPE_HTML
                body.image != null -> SHOW_TIME_IMAGE
                body.code != null -> SHOW_TYPE_CODE
                body.raw != null -> SHOW_TYPE_RAW
                else -> SHOW_TYPE_BYTES
            }
        )
    }

    val segmentedButtons = buildList {
        if (body.html != null) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.response_view_html),
                    selected = selectedDisplayMode == SHOW_TYPE_HTML,
                    onClick = { selectedDisplayMode = SHOW_TYPE_HTML },
                )
            )
        }
        if (body.image != null) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.response_view_image),
                    selected = selectedDisplayMode == SHOW_TIME_IMAGE,
                    onClick = { selectedDisplayMode = SHOW_TIME_IMAGE },
                )
            )
        }
        if (body.code != null) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.response_view_code),
                    selected = selectedDisplayMode == SHOW_TYPE_CODE,
                    onClick = { selectedDisplayMode = SHOW_TYPE_CODE },
                )
            )
        }
        if (body.raw != null) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.response_view_raw),
                    selected = selectedDisplayMode == SHOW_TYPE_RAW,
                    onClick = { selectedDisplayMode = SHOW_TYPE_RAW },
                )
            )
        }
        if (body.bytes != null && body.bytes.isNotEmpty()) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.response_view_binary),
                    selected = selectedDisplayMode == SHOW_TYPE_BYTES,
                    onClick = { selectedDisplayMode = SHOW_TYPE_BYTES },
                )
            )
        }
    }

    SingleChoiceSegmentedButtonRow(
        modifier = modifier.horizontalScroll(rememberScrollState()),
    ) {
        segmentedButtons.forEachIndexed { index, item ->
            SegmentedButton(
                selected = item.selected,
                onClick = item.onClick,
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = segmentedButtons.size
                )
            ) {
                Text(text = item.text)
            }
        }
    }

    when {
        body.image != null && selectedDisplayMode == SHOW_TIME_IMAGE ->
            AsyncImage(
                model = body.image,
                contentDescription = null,
                modifier = Modifier.horizontalScroll(rememberScrollState()).padding(top = 8.dp),
            )

        body.html != null && selectedDisplayMode == SHOW_TYPE_HTML ->
            SelectionContainer {
                Text(text = body.html, modifier = Modifier.horizontalScroll(rememberScrollState()))
            }

        body.code != null && selectedDisplayMode == SHOW_TYPE_CODE ->
            SelectionContainer {
                Text(text = body.code, modifier = Modifier.horizontalScroll(rememberScrollState()))
            }

        body.raw != null && selectedDisplayMode == SHOW_TYPE_RAW ->
            SelectionContainer {
                Text(text = body.raw, modifier = Modifier.horizontalScroll(rememberScrollState()))
            }

        body.bytes != null && body.bytes.isNotEmpty() && selectedDisplayMode == SHOW_TYPE_BYTES ->
            SelectionContainer {
                Text(text = body.bytes, modifier = Modifier.horizontalScroll(rememberScrollState()))
            }
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

@Composable
private fun NoBody() {
    Text(
        text = stringResource(Res.string.no_body),
        fontStyle = FontStyle.Italic,
        style = MaterialTheme.typography.bodyMedium,
    )
    return
}

@Composable
private fun Error(error: String) {
    Icon(
        imageVector = Icons.Filled.Warning,
        contentDescription = stringResource(Res.string.error),
        tint = MaterialTheme.colorScheme.error,
    )
    SelectionContainer {
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.horizontalScroll(rememberScrollState()).padding(top = 8.dp),
        )
    }
}

private data class BodyShowTypeSegment(
    val text: String,
    val selected: Boolean,
    val onClick: () -> Unit,
)
