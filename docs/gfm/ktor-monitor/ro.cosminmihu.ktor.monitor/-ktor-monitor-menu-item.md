//[ktor-monitor](../../index.md)/[ro.cosminmihu.ktor.monitor](index.md)/[KtorMonitorMenuItem](-ktor-monitor-menu-item.md)

# KtorMonitorMenuItem

[desktop]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [MenuScope](https://developer.android.com/reference/kotlin/androidx/compose/ui/window/MenuScope.html).[KtorMonitorMenuItem](-ktor-monitor-menu-item.md)(onClick: () -&gt; [Unit](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unit/index.html))

A [Tray](https://developer.android.com/reference/kotlin/androidx/compose/ui/window/package-summary.html) menu item for Ktor Monitor.

```kotlin
var showWindow by rememberSaveable { mutableStateOf(false) }

Tray(
     icon = painterResource(Res.drawable.ic_launcher),
     menu = {
        KtorMonitorMenuItem { showWindow = true }
     }
)

KtorMonitorWindow(
     show = showWindow,
     onCloseRequest = { showWindow = false },
)
```