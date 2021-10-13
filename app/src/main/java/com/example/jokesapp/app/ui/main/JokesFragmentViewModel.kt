package com.example.jokesapp.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.data.network.ResponseWrapper
import com.example.jokesapp.domain.useCases.RequestJokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokesFragmentViewModel @Inject constructor(
    private val jokesUseCase: RequestJokesUseCase
) : ViewModel() {
    private val _mutableJokesFlow = MutableLiveData<ResponseWrapper>(ResponseWrapper.Loading)
    val mutableJokesFlow: LiveData<ResponseWrapper> get() = _mutableJokesFlow

    init {
        requestJokes()
    }

    fun requestJokes() {
        viewModelScope.launch(IO) {
            val jokes = jokesUseCase()
            if (jokes.isNotEmpty())
                _mutableJokesFlow.postValue(ResponseWrapper.Success(jokes))
            else
                _mutableJokesFlow.postValue(ResponseWrapper.Error())

        }
    }
}