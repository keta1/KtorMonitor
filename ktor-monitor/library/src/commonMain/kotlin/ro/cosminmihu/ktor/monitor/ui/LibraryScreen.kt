package ro.cosminmihu.ktor.monitor.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
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
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import ro.cosminmihu.ktor.monitor.ui.detail.DetailRoute
import ro.cosminmihu.ktor.monitor.ui.list.ListRoute

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun LibraryScreen(
    modifier: Modifier = Modifier,
) {
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