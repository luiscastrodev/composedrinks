package di
import data.remote.DrinkRepositoryImpl
import di.Modules.provideRepositoryModule
import di.Modules.providehttpClientModule
import di.Modules.provideviewModelModule
import domain.repository.DrinkRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import presentation.drinks.DrinkViewModel

object Modules {

    @OptIn(ExperimentalSerializationApi::class)
    val providehttpClientModule = module {
        single {
            HttpClient {
                install(HttpTimeout) {
                    socketTimeoutMillis = 60_000
                    requestTimeoutMillis = 60_000
                }
                defaultRequest {
                    header("Content-Type", "application/json")
                }
                install(Logging) {
                    logger = Logger.SIMPLE
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {

                    json(
                        json = Json
                        {
                            ignoreUnknownKeys = true
                            explicitNulls = true
                        },

                        contentType = ContentType.Any
                    )
                }
            }
        }
    }

    val provideRepositoryModule = module {
        single<DrinkRepository> { DrinkRepositoryImpl(get()) }
    }

    val provideviewModelModule = module {
        factory<DrinkViewModel> { DrinkViewModel(get()) }
    }

}
