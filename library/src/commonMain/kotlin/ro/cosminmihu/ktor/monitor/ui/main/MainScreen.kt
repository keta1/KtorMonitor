package ro.cosminmihu.ktor.monitor.ui.main

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DisabledByDefault
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneExpansionAnchor
import androidx.compose.material3.adaptive.layout.rememberPaneExpansionState
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ro.cosminmihu.ktor.monitor.api.LoggingConfig
import ro.cosminmihu.ktor.monitor.ui.Dimens
import ro.cosminmihu.ktor.monitor.ui.detail.DetailRoute
import ro.cosminmihu.ktor.monitor.ui.list.ListRoute
import ro.cosminmihu.ktor.monitor.ui.resources.Res
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_library_disabled

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
) {
    // Check if the library is active.
    val config = koinInject<LoggingConfig>()
    if (!config.isActive) {
        EmptyState(modifier)
        return
    }

    val coroutineScope = rememberCoroutineScope()
    val navigator = rememberListDetailPaneScaffoldNavigator<String?>()

    // TODO add https://github.com/JetBrains/compose-multiplatform-core/pull/1771/files https://youtrack.jetbrains.com/issue/CMP-4419

    Surface(modifier = modifier) {
        ListDetailPaneScaffold(
            modifier = Modifier.fillMaxSize(),
            directive = navigator.scaffoldDirective,
            value = navigator.scaffoldValue,
            listPane = {
                AnimatedPane {
                    ListRoute { id ->
                        coroutineScope.launch {
                            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, id)
                        }
                    }
                }
            },
            detailPane = {
                val id = navigator.currentDestination?.contentKey
                AnimatedPane {
                    DetailRoute(
                        id = id,
                        onBack = {
                            coroutineScope.launch {
                                navigator.navigateBack()
                            }
                        }
                    )
                }
            },
            paneExpansionDragHandle = {
                rememberPaneExpansionState(
                    keyProvider = navigator.scaffoldValue,
                    anchors = listOf(
                        PaneExpansionAnchor.Proportion(0.3f),
                        PaneExpansionAnchor.Proportion(0.7f),
                    )
                )
                remember { MutableInteractionSource() }

                // TODO enable when include in jetbrains
//            VerticalDragHandle(
//                modifier = Modifier.paneExpansionDraggable(
//                    state,
//                    LocalMinimumInteractiveComponentSize.current,
//                    interactionSource
//                ),
//                interactionSource = interactionSource
//            )
            }
        )
    }
}

@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        val string = stringResource(Res.string.ktor_library_disabled)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.semantics(true) { string },
        ) {
            Icon(
                imageVector = Icons.Default.DisabledByDefault,
                contentDescription = null,
                modifier = Modifier.size(Dimens.ExtraLarge)
            )
            Text(
                text = string,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = Dimens.Medium)
            )
        }
    }
}