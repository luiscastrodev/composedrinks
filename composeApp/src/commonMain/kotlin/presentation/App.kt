package presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import di.AppModule
import org.koin.compose.KoinApplication
import presentation.drinks.DrinkScreen

@Composable
fun App() {
    KoinApplication(application = {
        modules(AppModule().appModule())
    }) {
        MaterialTheme(
        ) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Navigator(DrinkScreen)
            }
        }
    }
}