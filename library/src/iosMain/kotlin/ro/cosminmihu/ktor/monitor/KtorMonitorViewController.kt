package ro.cosminmihu.ktor.monitor

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

/**
 * [KtorMonitor] [UIViewController].
 */
public fun KtorMonitorViewController(): UIViewController = ComposeUIViewController { KtorMonitor() }