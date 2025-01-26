package ro.cosminmihu.ktor.monitor.library.ui.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.Res
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.clean
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.filter
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.library_name
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.list_empty
import ro.cosminmihu.ktor.monitor.library.ui.components.CallItem
import ro.cosminmihu.ktor.monitor.library.ui.components.SearchField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ListScreen(
    modifier: Modifier,
    uiState: ListUiState,
    setSearchQuery: (String) -> Unit,
    clearSearchQuery: () -> Unit,
    deleteCalls: () -> Unit,
    onCallClick: (String) -> Unit,
) {
    var showSearchBar by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        TopAppBar(
            windowInsets = WindowInsets(0), // TODO remove after jetbrains fix
            title = {
                Text(text = stringResource(Res.string.library_name))
            },
            actions = {
                IconButton(onClick = { showSearchBar = !showSearchBar }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(Res.string.filter)
                    )
                }
                IconButton(onClick = deleteCalls) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(Res.string.clean)
                    )
                }
            }
        )

        AnimatedVisibility(visible = showSearchBar) {
            SearchField(
                onSearch = setSearchQuery,
                onClose = {
                    clearSearchQuery()
                    showSearchBar = false
                }
            )
        }

        when {
            uiState.isEmpty -> {
                Text(
                    text = stringResource(Res.string.list_empty),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.weight(1f).fillMaxWidth()
                ) {
                    itemsIndexed(uiState.calls) { index, item ->
                        if (index == 0) {
                            HorizontalDivider()
                        }
                        CallItem(
                            call = item,
                            modifier = Modifier.clickable { onCallClick(item.id) }
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}
