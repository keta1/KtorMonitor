---
title: KtorMonitorWindow
---
//[ktor-monitor](../../index.html)/[ro.cosminmihu.ktor.monitor](index.html)/[KtorMonitorWindow](-ktor-monitor-window.html)



# KtorMonitorWindow



[desktop]\




@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)



fun [KtorMonitorWindow](-ktor-monitor-window.html)(show: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = true, onCloseRequest: () -&gt; [Unit](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unit/index.html) = {})



Ktor Monitor [Window](https://developer.android.com/reference/kotlin/androidx/compose/ui/window/package-summary.html).

```kotlin
var showWindow by rememberSaveable { mutableStateOf(false) }

KtorMonitorWindow(
     show = showWindow,
     onCloseRequest = { showWindow = false },
)
```


