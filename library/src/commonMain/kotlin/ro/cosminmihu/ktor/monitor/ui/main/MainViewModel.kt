package ro.cosminmihu.ktor.monitor.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ro.cosminmihu.ktor.monitor.domain.ConfigUseCase
import kotlin.time.Duration.Companion.seconds

internal class MainViewModel(
    setupUseCase: ConfigUseCase,
) : ViewModel() {

    val uiState = setupUseCase.isSetupDone
        .map { MainUiState(isLibraryReady = it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            MainUiState()
        )
}
