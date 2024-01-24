package presentation.state

import domain.models.Drink

sealed class UIState {
    data object Init : UIState()
    data object Loading : UIState()
    data class Success(val data: List<Drink>) : UIState()
    data class Error(val message: String) : UIState()
}