package ro.cosminmihu.ktor.monitor.library.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.Res
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.summary
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.request
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.response

private const val PAGE_COUNT = 3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailUiState,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { PAGE_COUNT }

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0) // TODO remove after jetbrains fix
    ) {
        Column(
            modifier = Modifier.padding(it).fillMaxWidth()
        ) {

            if (uiState.call == null || uiState.summary == null) {
                return@Column
            }

            PrimaryTabRow(selectedTabIndex = pagerState.currentPage) {
                Tab(
                    selected = pagerState.currentPage == 0,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } },
                ) {
                    Text(
                        text = stringResource(Res.string.summary),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
                Tab(
                    selected = pagerState.currentPage == 1,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1) } },
                ) {
                    Text(
                        text = stringResource(Res.string.request),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
                Tab(
                    selected = pagerState.currentPage == 2,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(2) } },
                ) {
                    Text(
                        text = stringResource(Res.string.response),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                when (page) {
                    0 -> SummaryScreen(
                        summary = uiState.summary,
                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                    )

                    1 -> RequestScreen(
                        request = uiState.call.request,
                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                    )

                    2 -> ResponseScreen(
                        response = uiState.call.response,
                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}