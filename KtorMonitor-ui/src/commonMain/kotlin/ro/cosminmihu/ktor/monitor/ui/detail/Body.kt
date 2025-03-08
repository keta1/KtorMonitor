package ro.cosminmihu.ktor.monitor.ui.detail

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import ro.cosminmihu.ktor.monitor.ui.Dimens
import ro.cosminmihu.ktor.monitor.ui.resources.Res
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_body_trimmed
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_response_view_binary
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_response_view_code
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_response_view_html
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_response_view_image
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_response_view_raw

internal fun LazyListScope.Body(
    body: DetailUiState.Body,
    displayMode: DisplayMode,
    onDisplayMode: (DisplayMode) -> Unit,
) {
    item {
        DisplayModeSelector(
            body = body,
            displayMode = displayMode,
            onDisplayMode = onDisplayMode,
        )
    }

    when {
        body.image != null && displayMode == DisplayMode.IMAGE -> item {
            AsyncImage(
                model = body.image,
                contentDescription = null,
                modifier = Modifier.horizontalScroll(rememberScrollState())
            )
        }

        body.html != null && displayMode == DisplayMode.HTML -> item {
            SelectionContainer {
                Text(text = body.html)
            }
        }

        body.code != null && displayMode == DisplayMode.CODE -> item {
            SelectionContainer {
                Text(text = body.code)
            }
        }

        body.raw != null && displayMode == DisplayMode.RAW -> item {
            SelectionContainer {
                Text(text = body.raw)
            }
        }

        body.bytes != null && body.bytes.isNotEmpty() && displayMode == DisplayMode.BYTES -> item {
            SelectionContainer {
                Text(text = body.bytes)
            }
        }
    }
}

@Composable
private fun DisplayModeSelector(
    body: DetailUiState.Body,
    displayMode: DisplayMode,
    onDisplayMode: (DisplayMode) -> Unit,
    modifier: Modifier = Modifier,
) {
    val segmentedButtons = buildList {
        if (body.html != null) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.ktor_response_view_html),
                    selected = displayMode == DisplayMode.HTML,
                    onClick = { onDisplayMode(DisplayMode.HTML) },
                )
            )
        }
        if (body.image != null) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.ktor_response_view_image),
                    selected = displayMode == DisplayMode.IMAGE,
                    onClick = { onDisplayMode(DisplayMode.IMAGE) },
                )
            )
        }
        if (body.code != null) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.ktor_response_view_code),
                    selected = displayMode == DisplayMode.CODE,
                    onClick = { onDisplayMode(DisplayMode.CODE) },
                )
            )
        }
        if (body.raw != null) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.ktor_response_view_raw),
                    selected = displayMode == DisplayMode.RAW,
                    onClick = { onDisplayMode(DisplayMode.RAW) },
                )
            )
        }
        if (body.bytes != null && body.bytes.isNotEmpty()) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.ktor_response_view_binary),
                    selected = displayMode == DisplayMode.BYTES,
                    onClick = { onDisplayMode(DisplayMode.BYTES) },
                )
            )
        }
    }

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        if (body.isTrimmed) {
            Text(
                text = stringResource(Res.string.ktor_body_trimmed),
                modifier = Modifier.padding(
                    vertical = Dimens.Small,
                    horizontal = Dimens.Medium
                ).align(Alignment.CenterStart),
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .align(Alignment.CenterEnd),
        ) {
            segmentedButtons.forEachIndexed { index, item ->
                val modifier = when(index) {
                    0 -> Modifier.padding(start = Dimens.Small)
                    segmentedButtons.lastIndex -> Modifier.padding(end = Dimens.Small)
                    else -> Modifier
                }

                SegmentedButton(
                    selected = item.selected,
                    onClick = item.onClick,
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index, count = segmentedButtons.size
                    ),
                    modifier = modifier,
                ) {
                    Text(text = item.text)
                }
            }
        }
    }
}

private data class BodyShowTypeSegment(
    val text: String,
    val selected: Boolean,
    val onClick: () -> Unit,
)

internal enum class DisplayMode {
    HTML, IMAGE, CODE, RAW, BYTES
}