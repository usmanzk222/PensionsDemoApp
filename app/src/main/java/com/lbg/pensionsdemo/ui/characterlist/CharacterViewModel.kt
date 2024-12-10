package com.lbg.pensionsdemo.ui.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbg.pensionsdemo.domain.model.CharacterDomain
import com.lbg.pensionsdemo.domain.usecase.IPensionsUseCase
import com.lbg.pensionsdemo.ui.model.CharacterUiModel
import com.lbg.pensionsdemo.ui.model.mapToUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getAllCharactersUseCase: IPensionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val uiState: StateFlow<CharacterUiState> = _uiState

    init {
        getAllCharacters()
    }

    fun getAllCharacters() {

        viewModelScope.launch {
            _uiState.value = CharacterUiState.Loading
            getAllCharactersUseCase()
                .catch { cause: Throwable ->
                    _uiState.value = CharacterUiState.Error(
                         cause
                    )
                }
                .collect { result: Result<List<CharacterDomain>> ->
                    _uiState.value = when {
                        result.isSuccess -> CharacterUiState.Success(
                            result.getOrDefault(emptyList()).map { it.mapToUIModel() }
                        )

                        result.isFailure -> CharacterUiState.Error(
                            result.exceptionOrNull() ?: Throwable("Something went wrong")
                        )

                        else -> CharacterUiState.Loading
                    }
                }
        }
    }


    sealed class CharacterUiState {
        data object Loading : CharacterUiState()
        data class Success(val characters: List<CharacterUiModel>) : CharacterUiState()
        data class Error(val exception: Throwable) : CharacterUiState()
    }
}