package pokemon.data.home.remote

import pokemon.abstraction.home.HomeRemoteDataSource
import pokemon.data.base.getIdFromPokemonUrl
import pokemon.data.home.network.HomeService
import pokemon.model.home.PokemonItems
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(private val homeService: HomeService) : HomeRemoteDataSource {
    override suspend fun getPokemons(): List<PokemonItems> {
        val response = homeService.getPokemons()
        val result = response.results
        return result?.map {
            PokemonItems(
                name = it.name.orEmpty(),
                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${
                    getIdFromPokemonUrl(
                        it.url.orEmpty()
                    )
                }.png"

            )
        } ?: listOf()
    }
}