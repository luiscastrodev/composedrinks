package presentation.drinks

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import presentation.state.UIState

object DrinkScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<DrinkViewModel>()
        val state by viewModel.state.collectAsState()

        when (state) {
            is UIState.Success -> {

            }
            is UIState.Error -> {
                Text(
                    text = (state as UIState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            is UIState.Loading -> { CircularProgressIndicator(modifier = Modifier) }
            else -> {println("Init")}
        }
    }
}