package ro.cosminmihu.ktor.monitor.ui.components

import androidx.compose.foundation.layout.WindowInsets

@Deprecated("Temporary workaround for missing WindowInsets")
internal val WindowInsets.Companion.temporaryWindowInsets
    get() = WindowInsets(0) // TODO remove after jetbrains fix