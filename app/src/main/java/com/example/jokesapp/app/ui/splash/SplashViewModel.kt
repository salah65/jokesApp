package com.example.jokesapp.app.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.domain.useCases.SessionCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionCountUseCase: SessionCountUseCase
) : ViewModel() {
    private val _timerState: Channel<SplashState> = Channel()
    val timerState get() = _timerState.receiveAsFlow()

    init {
        cacheSessionCount()
        startTimer()
    }

    fun getAppOpenTimes(): Int {
        return sessionCountUseCase.getSessionCount()
    }

    private fun cacheSessionCount() {
        sessionCountUseCase.cacheSessionCount()
    }

    private fun startTimer() {
        viewModelScope.launch {
            delay(3000L)
            _timerState.send(SplashState.Finish)

        }
    }
}

enum class SplashState {
    Finish
}