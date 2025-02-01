package ro.cosminmihu.ktor.monitor.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import ro.cosminmihu.ktor.monitor.ui.Dimens
import ro.cosminmihu.ktor.monitor.ui.resources.Res
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_back
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_request
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_response
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_summary

private const val PAGE_COUNT = 3

private const val PAGE_INDEX_SUMMARY = 0
private const val PAGE_INDEX_REQUEST = 1
private const val PAGE_INDEX_RESPONSE = 2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailScreen(
    uiState: DetailUiState,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { PAGE_COUNT }

    Scaffold(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(it).fillMaxWidth()
        ) {

            if (uiState.call == null || uiState.summary == null) {
                return@Column
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(TopAppBarDefaults.TopAppBarExpandedHeight)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight().width(IntrinsicSize.Max)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = Dimens.Small, top = Dimens.Small),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                                contentDescription = stringResource(Res.string.ktor_back),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                }

                PrimaryTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    modifier = Modifier.align(Alignment.Bottom)
                ) {
                    Tab(
                        selected = pagerState.currentPage == PAGE_INDEX_SUMMARY,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(PAGE_INDEX_SUMMARY)
                            }
                        },
                    ) {
                        Text(
                            text = stringResource(Res.string.ktor_summary),
                            modifier = Modifier.padding(vertical = Dimens.Medium)
                        )
                    }
                    Tab(
                        selected = pagerState.currentPage == PAGE_INDEX_REQUEST,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(PAGE_INDEX_REQUEST)
                            }
                        },
                    ) {
                        Text(
                            text = stringResource(Res.string.ktor_request),
                            modifier = Modifier.padding(vertical = Dimens.Medium)
                        )
                    }
                    Tab(
                        selected = pagerState.currentPage == PAGE_INDEX_RESPONSE,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(PAGE_INDEX_RESPONSE)
                            }
                        },
                    ) {
                        Text(
                            text = stringResource(Res.string.ktor_response),
                            modifier = Modifier.padding(vertical = Dimens.Medium)
                        )
                    }
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                when (page) {
                    PAGE_INDEX_SUMMARY -> SummaryScreen(
                        summary = uiState.summary,
                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                    )

                    PAGE_INDEX_REQUEST -> RequestScreen(
                        request = uiState.call.request,
                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                    )

                    PAGE_INDEX_RESPONSE -> ResponseScreen(
                        response = uiState.call.response,
                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}