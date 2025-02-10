//[ktor-monitor](../../index.md)/[ro.cosminmihu.ktor.monitor](index.md)/[KtorMonitorViewController](-ktor-monitor-view-controller.md)

# KtorMonitorViewController

[ios]\
fun [KtorMonitorViewController](-ktor-monitor-view-controller.md)(): UIViewController

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
                .ignoresSafeArea(.keyboard)
    }
}
```