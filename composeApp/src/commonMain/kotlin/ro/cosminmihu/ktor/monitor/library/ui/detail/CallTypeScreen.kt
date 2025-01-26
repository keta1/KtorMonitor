package ro.cosminmihu.ktor.monitor.library.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassTop
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.Res
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.error
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.in_progress
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.no_body
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.response_view_binary
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.response_view_code
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.response_view_html
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.response_view_image
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.response_view_raw
import ro.cosminmihu.ktor.monitor.library.ui.components.RotatingImage

@Composable
fun CallTypeScreen(
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
            RotatingImage(
                imageVector = Icons.Filled.HourglassTop,
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = stringResource(Res.string.in_progress),
                imageRotation = true,
            )
            return
        }

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

        Spacer(modifier = Modifier.padding(8.dp))

        if (isError) {
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
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            return
        }

        if (body == null || body.bytes == null || body.bytes.isEmpty() == true) {
            Text(text = stringResource(Res.string.no_body))
            return
        }

        var selectedDisplayMode by remember(body) {
            mutableIntStateOf(
                when {
                    body.html != null -> 0
                    body.image != null -> 1
                    body.code != null -> 2
                    body.raw != null -> 3
                    else -> 4
                }
            )
        }
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.align(Alignment.End)
        ) {
            if (body.html != null) {
                SegmentedButton(
                    modifier = Modifier.padding(start = 8.dp),
                    selected = selectedDisplayMode == 0,
                    onClick = { selectedDisplayMode = 0 },
                    shape = SegmentedButtonDefaults.baseShape,
                ) {
                    Text(text = stringResource(Res.string.response_view_html))
                }
            }

            if (body.image != null) {
                SegmentedButton(
                    modifier = Modifier.padding(start = 8.dp),
                    selected = selectedDisplayMode == 1,
                    onClick = { selectedDisplayMode = 1 },
                    shape = SegmentedButtonDefaults.baseShape,
                ) {
                    Text(text = stringResource(Res.string.response_view_image))
                }
            }

            if (body.code != null) {
                SegmentedButton(
                    modifier = Modifier.padding(start = 8.dp),
                    selected = selectedDisplayMode == 2,
                    onClick = { selectedDisplayMode = 2 },
                    shape = SegmentedButtonDefaults.baseShape,
                ) {
                    Text(text = stringResource(Res.string.response_view_code))
                }
            }

            if (body.raw != null) {
                SegmentedButton(
                    modifier = Modifier.padding(start = 8.dp),
                    selected = selectedDisplayMode == 3,
                    onClick = { selectedDisplayMode = 3 },
                    shape = SegmentedButtonDefaults.baseShape,
                ) {
                    Text(text = stringResource(Res.string.response_view_raw))
                }
            }
            if (body.bytes.isNotEmpty()) {
                SegmentedButton(
                    modifier = Modifier.padding(start = 8.dp),
                    selected = selectedDisplayMode == 4,
                    onClick = { selectedDisplayMode = 4 },
                    shape = SegmentedButtonDefaults.baseShape,
                ) {
                    Text(text = stringResource(Res.string.response_view_binary))
                }
            }
        }
        if (body.html != null && selectedDisplayMode == 0) {
            SelectionContainer {
                Text(text = body.html)
            }
        }

        if (body.image != null && selectedDisplayMode == 1) {
            AsyncImage(
                model = body.image,
                contentDescription = null,
                modifier = Modifier.padding(top = 8.dp),
            )
        }

        if (body.code != null && selectedDisplayMode == 2) {
            SelectionContainer {
                Text(text = body.code)
            }
        }

        if (body.raw != null && selectedDisplayMode == 3) {
            SelectionContainer {
                Text(text = body.raw)
            }
        }

        if (body.bytes.isNotEmpty() && selectedDisplayMode == 4) {
            Text(text = body.bytes)
        }
    }
}
