# KtorMonitor
Powerful tools to log [Ktor Client](https://ktor.io/) requests and responses, making it easier to debug and analyze network communication.

## SETUP

### Gradle

```kotlin
// projectDir/composeApp/build.gradle.kts

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("ro.cosminmihu.ktor:ktor-monitor-logging:1.0.0")
        }
    }
}
```

### Common

```kotlin
// projectDir/composeApp/src/commonMain/kotlin/HttpClient.kt

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

### Android

```kotlin
// projectDir/composeApp/src/androidMain/kotlin/MyApp.kt

class MyApp: Application() {  
    init {  
        KtorMonitor.init(this)  
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

```kotlin
// projectDir/composeApp/src/desktopMain/kotlin/main.kt

fun main() = application {

    var showKtorMonitor by rememberSaveable { mutableStateOf(false) }
    KtorMonitorWindow(
        onCloseRequest = { showKtorMonitor = false },
        show = showKtorMonitor
    )

}
```

* Use ```KtorMonitorWindow``` Composable Wrapper with ```KtorMonitorMenuItem```

```kotlin
// projectDir/composeApp/src/desktopMain/kotlin/main.kt

fun main() = application {

    var showKtorMonitor by rememberSaveable { mutableStateOf(false) }
    Tray(
        icon = painterResource(Res.drawable.ic_launcher),
        menu = {
            KtorMonitorMenuItem { showKtorMonitor = true }
        }
    )

    KtorMonitorWindow(
        onCloseRequest = { showKtorMonitor = false },
        show = showKtorMonitor
    )

}
```

* Use ```KtorMonitorPanel``` Swing Panel Wrapper

```kotlin
// projectDir/composeApp/src/desktopMain/kotlin/main.kt

fun main() = application {

    SwingUtilities.invokeLater {
        val frame = JFrame()
        frame.add(KtorMonitorPanel, BorderLayout.CENTER)
    }

}
```
