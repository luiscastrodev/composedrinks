package domain.repository

import domain.models.Drink

interface DrinkRepository {
    suspend fun search(): common.Resource<List<Drink>>
}