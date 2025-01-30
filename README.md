# KtorMonitor
Powerful tools to log [Ktor Client](https://ktor.io/) requests and responses, making it easier to debug and analyze network communication.

## Common: Ktor Client Configuration

```kotlin
// projectDir/composeApp/src/commonMain/kotlin/com/example/api/HttpClient.kt

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

## Android: KtorMonitor Initialization

```kotlin
// projectDir/composeApp/src/androidMain/kotlin/com/example/MyApp.kt

class MyApp: Application() {  
    init {  
        KtorMonitor.init(this)  
    }  
}
```

## Desktop

```kotlin
// projectDir/composeApp/src/desktopMain/kotlin/com/example/compose/main.kt

fun main() = application {

    var showKtorMonitor by rememberSaveable { mutableStateOf(false) }  
	KtorMonitorWindow(  
        onCloseRequest = { showKtorMonitor = false },  
        show = showKtorMonitor  
    )  
	
}
```

```kotlin
// projectDir/composeApp/src/desktopMain/kotlin/com/example/compose/main.kt

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

```kotlin
// projectDir/composeApp/src/desktopMain/kotlin/com/example/swing/main.kt

fun main() = application {

    SwingUtilities.invokeLater {
        val frame = JFrame()
        frame.add(KtorMonitorPanel, BorderLayout.CENTER)
    }

}
```
