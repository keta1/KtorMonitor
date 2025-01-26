package ro.cosminmihu.ktor.monitor.library.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.Res
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.error
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.in_progress
import ro.cosminmihu.ktor.monitor.library.domain.model.ContentType
import ro.cosminmihu.ktor.monitor.library.domain.model.asColor
import ro.cosminmihu.ktor.monitor.library.ui.list.ListUiState
import ro.cosminmihu.ktor.monitor.library.ui.list.isError
import ro.cosminmihu.ktor.monitor.library.ui.list.isLoading

@Composable
internal fun CallItem(
    call: ListUiState.Call,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.height(IntrinsicSize.Max).padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight().weight(0.2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            when {
                call.isError -> Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = stringResource(Res.string.error),
                    tint = MaterialTheme.colorScheme.error,
                )

                call.isLoading -> CircularProgressIndicator()
                else -> {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = call.response.responseCode.toString(),
                        fontWeight = FontWeight.Bold,
                        color = if (call.isError) MaterialTheme.colorScheme.error else Color.Unspecified,
                    )
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = call.response.contentType.contentName,
                        fontWeight = FontWeight.Bold,
                        color = call.response.contentType.asColor,
                    )
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxHeight().weight(0.8f)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = call.request.method + " " + call.request.pathAndQuery,
                fontWeight = FontWeight.Bold,
                color = if (call.isError) MaterialTheme.colorScheme.error else Color.Unspecified,
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = when (call.isSecure) {
                    true -> """🔒${call.request.host}"""
                    else -> call.request.host
                },
            )
            when {
                call.isLoading -> Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(Res.string.in_progress),
                    fontStyle = FontStyle.Italic,
                )

                call.isError -> Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = call.response.error,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.error,
                    maxLines = 3
                )

                else -> {
                    Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                        Text(
                            text = call.request.requestTime,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = call.response.duration,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = call.response.size,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CallItemPreview() {
    val call = ListUiState.Call(
        id = "1",
        isSecure = true,
        request = ListUiState.Request(
            method = "GET",
            host = "www.example.com",
            pathAndQuery = "/path/to/resource?param1=value1&param2=value2",
            requestTime = "2023-05-01 12:34:56",
        ),
        response = ListUiState.Response(
            responseCode = "200",
            contentType = ContentType.APPLICATION_JSON,
            duration = "123 ms",
            size = "123 KB",
            error = ""
        )
    )
    CallItem(call)
}