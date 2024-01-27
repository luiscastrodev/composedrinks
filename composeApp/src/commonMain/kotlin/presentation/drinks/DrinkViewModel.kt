package presentation.drinks

import common.Resource
import domain.repository.DrinkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.state.UIState
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class DrinkViewModel(
    val drinkRepository: DrinkRepository
) : ViewModel() {
    private val _state = MutableStateFlow<UIState>(UIState.Init)
    val state = _state.asStateFlow()

    init {
        search()
    }

    private fun search() {
        viewModelScope.launch {
            try {
                _state.emit(UIState.Loading)
                when (val result = drinkRepository.search()) {
                    is Resource.Error -> {
                        _state.emit(UIState.Error(result.message!!))
                    }
                    is Resource.Success -> {
                        _state.emit(UIState.Success(result.data!!))
                    }
                    else -> {
                        println("Loading repository!!")
                    }
                }
            } catch (e: Exception) {
                _state.emit(UIState.Error(e.message.toString()))
            }
        }
    }
}