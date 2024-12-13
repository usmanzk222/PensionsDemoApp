package com.lbg.pensionsdemo.ui.greetings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbg.pensionsdemo.domain.usecase.IGetUserUseCase
import com.lbg.pensionsdemo.ui.model.UserUiModel
import com.lbg.pensionsdemo.ui.model.mapToUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GreetingsViewModel @Inject constructor(
    private val getUserUseCase: IGetUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<GreetingsUIState>(GreetingsUIState.Loading)
    val uiState: StateFlow<GreetingsUIState> = _uiState

    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            _uiState.value = GreetingsUIState.Loading
            _uiState.value = try {
                val domainUser = getUserUseCase()
                 if (domainUser != null){
                    GreetingsUIState.Success(domainUser.mapToUIModel())
                }else{
                    GreetingsUIState.Error(Exception("User not found"))
                }
            }catch (e: Exception){
                GreetingsUIState.Error(e)
            }
        }
    }


    sealed class GreetingsUIState {
        data object Loading : GreetingsUIState()
        data class Success(val user: UserUiModel) : GreetingsUIState()
        data class Error(val exception: Throwable) : GreetingsUIState()
    }
}