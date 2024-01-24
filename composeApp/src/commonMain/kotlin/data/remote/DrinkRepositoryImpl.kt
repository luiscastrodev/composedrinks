package data.remote

import common.Constants
import common.Resource
import data.remote.models.DrinkResponse
import domain.models.Drink
import domain.repository.DrinkRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class DrinkRepositoryImpl(
    private val httpClient: HttpClient
): DrinkRepository {

    override suspend fun search(): Resource<List<Drink>> = try {
        val drinkResponse = httpClient
            .get("${Constants.BASEURL.VALUE}${Constants.ROUTES.SEARCH}")
            .body<DrinkResponse>().drinkDTOS.map { it.toDomainModel() }
        Resource.Success(drinkResponse)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Unknown error")
    }

}