package ro.cosminmihu.ktor.monitor.ui.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ro.cosminmihu.ktor.monitor.ui.components.CallItem
import ro.cosminmihu.ktor.monitor.ui.components.Loading
import ro.cosminmihu.ktor.monitor.ui.components.SearchField
import ro.cosminmihu.ktor.monitor.ui.resources.Res
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_clean
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_filter
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_ic_logo
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_library_name
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_library_title
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_list_empty

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

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0), // TODO remove after jetbrains fix
        topBar = {
            TopAppBar(
                windowInsets = WindowInsets(0), // TODO remove after jetbrains fix
                title = {
                    Text(
                        text = stringResource(Res.string.ktor_library_title),
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    Image(
                        imageVector = vectorResource(Res.drawable.ktor_ic_logo),
                        contentDescription = stringResource(Res.string.ktor_library_name),
                        modifier = Modifier.size(40.dp)
                    )
                },
                actions = {
                    IconButton(onClick = { showSearchBar = !showSearchBar }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(Res.string.ktor_filter)
                        )
                    }
                    IconButton(onClick = deleteCalls) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = stringResource(Res.string.ktor_clean)
                        )
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it).fillMaxWidth()) {

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
                uiState.isLoading -> {
                    Loading.Medium(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                uiState.isEmpty -> {
                    Text(
                        text = stringResource(Res.string.ktor_list_empty),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                uiState.calls != null -> {
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
}
