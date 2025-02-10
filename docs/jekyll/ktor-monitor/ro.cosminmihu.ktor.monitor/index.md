---
title: ro.cosminmihu.ktor.monitor
---
//[ktor-monitor](../../index.html)/[ro.cosminmihu.ktor.monitor](index.html)



# Package-level declarations



## Types


| Name | Summary |
|---|---|
| [ContentLength](-content-length/index.html) | [common]<br>object [ContentLength](-content-length/index.html)<br>The maximum length of the content that will be logged. After this response body will be truncated. |
| [KtorMonitorLoggingConfig](-ktor-monitor-logging-config/index.html) | [common]<br>class [KtorMonitorLoggingConfig](-ktor-monitor-logging-config/index.html)<br>A configuration for the [KtorMonitorLogging](-ktor-monitor-logging.html) plugin. |
| [RetentionPeriod](-retention-period/index.html) | [common]<br>object [RetentionPeriod](-retention-period/index.html)<br>The retention period for the logs. |


## Properties


| Name | Summary |
|---|---|
| [KtorMonitorLogging](-ktor-monitor-logging.html) | [common]<br>val [KtorMonitorLogging](-ktor-monitor-logging.html): ClientPlugin&lt;[KtorMonitorLoggingConfig](-ktor-monitor-logging-config/index.html)&gt;<br>A [Ktor](https://ktor.io/) client plugin that provides the capability to log HTTP calls. |
| [KtorMonitorPanel](-ktor-monitor-panel.html) | [desktop]<br>val [KtorMonitorPanel](-ktor-monitor-panel.html): [JLayeredPane](https://docs.oracle.com/javase/8/docs/api/javax/swing/JLayeredPane.html)<br>A Swing [javax.swing.JPanel](https://docs.oracle.com/javase/8/docs/api/javax/swing/JPanel.html) that displays [KtorMonitor](-ktor-monitor.html). |


## Functions


| Name | Summary |
|---|---|
| [KtorMonitor](-ktor-monitor.html) | [common]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [KtorMonitor](-ktor-monitor.html)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, useKtorMonitorTheme: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = true)<br>Ktor Monitor UI entry point. |
| [KtorMonitorMenuItem](-ktor-monitor-menu-item.html) | [desktop]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [MenuScope](https://developer.android.com/reference/kotlin/androidx/compose/ui/window/MenuScope.html).[KtorMonitorMenuItem](-ktor-monitor-menu-item.html)(onClick: () -&gt; [Unit](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unit/index.html))<br>A [Tray](https://developer.android.com/reference/kotlin/androidx/compose/ui/window/package-summary.html) menu item for Ktor Monitor. |
| [KtorMonitorViewController](-ktor-monitor-view-controller.html) | [ios]<br>fun [KtorMonitorViewController](-ktor-monitor-view-controller.html)(): UIViewController<br>UIViewController for KtorMonitor. |
| [KtorMonitorWindow](-ktor-monitor-window.html) | [desktop]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [KtorMonitorWindow](-ktor-monitor-window.html)(show: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = true, onCloseRequest: () -&gt; [Unit](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unit/index.html) = {})<br>Ktor Monitor [Window](https://developer.android.com/reference/kotlin/androidx/compose/ui/window/package-summary.html). |
