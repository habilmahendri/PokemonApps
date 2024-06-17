package pokemon.data.home.remote

import pokemon.abstraction.home.HomeRemoteDataSource
import pokemon.data.home.network.HomeService
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(private val homeService: HomeService) : HomeRemoteDataSource {
    override suspend fun getPokemons(): List<String> {
        val response = homeService.getPokemons()
        val result = response.result
        return result?.map {
            it.name.orEmpty()
        } ?: listOf()
    }
}