package ro.cosminmihu.ktor.monitor.ui.detail

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontStyle
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import ro.cosminmihu.ktor.monitor.ui.Dimens
import ro.cosminmihu.ktor.monitor.ui.resources.Res
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_no_body
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_response_view_binary
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_response_view_code
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_response_view_html
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_response_view_image
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_response_view_raw

private const val SHOW_TYPE_HTML = 0
private const val SHOW_TIME_IMAGE = 1
private const val SHOW_TYPE_CODE = 2
private const val SHOW_TYPE_RAW = 3
private const val SHOW_TYPE_BYTES = 4

@Composable
internal fun ColumnScope.Body(body: DetailUiState.Body, modifier: Modifier = Modifier) {
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
                    text = stringResource(Res.string.ktor_response_view_html),
                    selected = selectedDisplayMode == SHOW_TYPE_HTML,
                    onClick = { selectedDisplayMode = SHOW_TYPE_HTML },
                )
            )
        }
        if (body.image != null) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.ktor_response_view_image),
                    selected = selectedDisplayMode == SHOW_TIME_IMAGE,
                    onClick = { selectedDisplayMode = SHOW_TIME_IMAGE },
                )
            )
        }
        if (body.code != null) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.ktor_response_view_code),
                    selected = selectedDisplayMode == SHOW_TYPE_CODE,
                    onClick = { selectedDisplayMode = SHOW_TYPE_CODE },
                )
            )
        }
        if (body.raw != null) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.ktor_response_view_raw),
                    selected = selectedDisplayMode == SHOW_TYPE_RAW,
                    onClick = { selectedDisplayMode = SHOW_TYPE_RAW },
                )
            )
        }
        if (body.bytes != null && body.bytes.isNotEmpty()) {
            add(
                BodyShowTypeSegment(
                    text = stringResource(Res.string.ktor_response_view_binary),
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
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(top = Dimens.Small),
            )

        body.html != null && selectedDisplayMode == SHOW_TYPE_HTML ->
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(body.html) {
                    SelectionContainer {
                        Text(text = it)
                    }
                }
            }

        body.code != null && selectedDisplayMode == SHOW_TYPE_CODE ->
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(body.code) {
                    SelectionContainer {
                        Text(text = it)
                    }
                }
            }

        body.raw != null && selectedDisplayMode == SHOW_TYPE_RAW ->
            SelectionContainer {
                Text(text = body.raw, modifier = Modifier.verticalScroll(rememberScrollState()))
            }

        body.bytes != null && body.bytes.isNotEmpty() && selectedDisplayMode == SHOW_TYPE_BYTES ->
            SelectionContainer {
                Text(text = body.bytes, modifier = Modifier.verticalScroll(rememberScrollState()))
            }
    }
}

@Composable
internal fun NoBody() {
    Text(
        text = stringResource(Res.string.ktor_no_body),
        fontStyle = FontStyle.Italic,
        style = MaterialTheme.typography.bodyMedium,
    )
    return
}

private data class BodyShowTypeSegment(
    val text: String,
    val selected: Boolean,
    val onClick: () -> Unit,
)