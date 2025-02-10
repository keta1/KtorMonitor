//[ktor-monitor](../../index.md)/[ro.cosminmihu.ktor.monitor](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [ContentLength](-content-length/index.md) | [common]<br>object [ContentLength](-content-length/index.md)<br>The maximum length of the content that will be logged. After this response body will be truncated. |
| [KtorMonitorLoggingConfig](-ktor-monitor-logging-config/index.md) | [common]<br>class [KtorMonitorLoggingConfig](-ktor-monitor-logging-config/index.md)<br>A configuration for the [KtorMonitorLogging](-ktor-monitor-logging.md) plugin. |
| [RetentionPeriod](-retention-period/index.md) | [common]<br>object [RetentionPeriod](-retention-period/index.md)<br>The retention period for the logs. |

## Properties

| Name | Summary |
|---|---|
| [KtorMonitorLogging](-ktor-monitor-logging.md) | [common]<br>val [KtorMonitorLogging](-ktor-monitor-logging.md): ClientPlugin&lt;[KtorMonitorLoggingConfig](-ktor-monitor-logging-config/index.md)&gt;<br>A [Ktor](https://ktor.io/) client plugin that provides the capability to log HTTP calls. |
| [KtorMonitorPanel](-ktor-monitor-panel.md) | [desktop]<br>val [KtorMonitorPanel](-ktor-monitor-panel.md): [JLayeredPane](https://docs.oracle.com/javase/8/docs/api/javax/swing/JLayeredPane.html)<br>A Swing [javax.swing.JPanel](https://docs.oracle.com/javase/8/docs/api/javax/swing/JPanel.html) that displays [KtorMonitor](-ktor-monitor.md). |

## Functions

| Name | Summary |
|---|---|
| [KtorMonitor](-ktor-monitor.md) | [common]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [KtorMonitor](-ktor-monitor.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, useKtorMonitorTheme: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = true)<br>Ktor Monitor UI entry point. |
| [KtorMonitorMenuItem](-ktor-monitor-menu-item.md) | [desktop]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [MenuScope](https://developer.android.com/reference/kotlin/androidx/compose/ui/window/MenuScope.html).[KtorMonitorMenuItem](-ktor-monitor-menu-item.md)(onClick: () -&gt; [Unit](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unit/index.html))<br>A [Tray](https://developer.android.com/reference/kotlin/androidx/compose/ui/window/package-summary.html) menu item for Ktor Monitor. |
| [KtorMonitorViewController](-ktor-monitor-view-controller.md) | [ios]<br>fun [KtorMonitorViewController](-ktor-monitor-view-controller.md)(): UIViewController<br>UIViewController for KtorMonitor. |
| [KtorMonitorWindow](-ktor-monitor-window.md) | [desktop]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [KtorMonitorWindow](-ktor-monitor-window.md)(show: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = true, onCloseRequest: () -&gt; [Unit](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unit/index.html) = {})<br>Ktor Monitor [Window](https://developer.android.com/reference/kotlin/androidx/compose/ui/window/package-summary.html). |