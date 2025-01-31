# KtorMonitor
Powerful tools to log [Ktor Client](https://ktor.io/) requests and responses, making it easier to debug and analyze network communication.

## SETUP

### Gradle

- in ```projectDir/settings.gradle.kts```

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("ro.cosminmihu.ktor:ktor-monitor-logging:1.0.0")
        }
    }
}
```

### Common

- in ```projectDir/composeApp/src/commonMain/kotlin/HttpClient.kt```

```kotlin
HttpClient {
	
    install(KtorMonitorLogging) {  
        sanitizeHeader { header -> header == "Authorization" }  
        filter { request -> !request.url.host.contains("cosminmihu.ro") }  
        isActive = true  
        showNotification = true  
        retentionPeriod = RetentionPeriod.OneHour  
    }
    
}
```

## USE

### Common / Android / Desktop

* Use ```KtorMonitor``` Composable

```kotlin
@Composable
fun Composable() {
    KtorMonitor()
}
```

### Desktop Only

* Use ```KtorMonitorWindow``` Composable Wrapper

- in ```projectDir/composeApp/src/desktopMain/kotlin/main.kt```

```kotlin
fun main() = application {

    var showKtorMonitor by rememberSaveable { mutableStateOf(false) }
    KtorMonitorWindow(
        onCloseRequest = { showKtorMonitor = false },
        show = showKtorMonitor
    )

}
```

* Use ```KtorMonitorWindow``` Composable Wrapper with ```KtorMonitorMenuItem```

- in ```projectDir/composeApp/src/desktopMain/kotlin/main.kt```

```kotlin
fun main() = application {

    var showKtorMonitor by rememberSaveable { mutableStateOf(false) }
    Tray(
        icon = painterResource(Res.drawable.ic_launcher),
        menu = {
            KtorMonitorMenuItem { showKtorMonitor = true }
        }
    )

    KtorMonitorWindow(
        show = showKtorMonitor,
        onCloseRequest = { showKtorMonitor = false }
    )

}
```

* Use ```KtorMonitorPanel``` Swing Panel Wrapper

- in ```projectDir/composeApp/src/desktopMain/kotlin/main.kt```

```kotlin
fun main() = application {

    SwingUtilities.invokeLater {
        val frame = JFrame()
        frame.add(KtorMonitorPanel, BorderLayout.CENTER)
        frame.isVisible = true
    }

}
```
