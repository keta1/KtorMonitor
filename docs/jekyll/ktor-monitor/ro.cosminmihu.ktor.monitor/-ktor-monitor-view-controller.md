---
title: KtorMonitorViewController
---
//[ktor-monitor](../../index.html)/[ro.cosminmihu.ktor.monitor](index.html)/[KtorMonitorViewController](-ktor-monitor-view-controller.html)



# KtorMonitorViewController



[ios]\
fun [KtorMonitorViewController](-ktor-monitor-view-controller.html)(): UIViewController



UIViewController for KtorMonitor.

```kotlin
fun MainViewController() = KtorMonitorViewController()
```
```swift
struct KtorMonitorView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        KtorMonitorView()
                .ignoresSafeArea()
    }
}
```


