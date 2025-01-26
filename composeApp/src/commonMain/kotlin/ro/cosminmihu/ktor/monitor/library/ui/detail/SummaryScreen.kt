package ro.cosminmihu.ktor.monitor.library.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SummaryScreen(summary: DetailUiState.Summary, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        KeyValue(key = "URL", value = summary.url)
        KeyValue(key = "Method", value = summary.method)
        KeyValue(key = "Protocol", value = summary.protocol)
        KeyValue(key = "Response Code", value = summary.responseCode)
        Spacer(modifier = Modifier.padding(8.dp))
        KeyValue(key = "Request time", value = summary.requestTime)
        KeyValue(key = "Response time", value = summary.responseTime)
        KeyValue(key = "Duration", value = summary.duration)
        Spacer(modifier = Modifier.padding(8.dp))
        KeyValue(key = "Request size", value = summary.requestSize)
        KeyValue(key = "Response size", value = summary.responseSize)
        KeyValue(key = "Total Size", value = summary.totalSize)
    }
}

@Composable
private fun KeyValue(key: String, value: String) {
    Row {
        Text(
            text = key,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.3f),
        )
        Text(
            text = value,
            modifier = Modifier.weight(0.7f),
        )
    }
}