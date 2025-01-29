package ro.cosminmihu.ktor.monitor.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun SearchField(
    onSearch: (String) -> Unit,
    onClose: () -> Unit,
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                onSearch(it)
            },
            singleLine = true,
            label = { Text("Filter") },  // TODO
        )

        IconButton(onClick = { onClose() }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Localized description" // TODO
            )
        }
    }
}

@Preview
@Composable
private fun SearchFieldPreview() {
    SearchField(
        onSearch = { },
        onClose = { },
    )
}