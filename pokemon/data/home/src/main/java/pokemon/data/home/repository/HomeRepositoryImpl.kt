package pokemon.data.home.repository

import pokemon.abstraction.home.HomeRemoteDataSource
import pokemon.abstraction.home.HomeRepository
import pokemon.model.home.PokemonItems
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeRemoteDataSource: HomeRemoteDataSource) : HomeRepository {
    override suspend fun getPokemons(): List<PokemonItems> {
        return homeRemoteDataSource.getPokemons()
    }
}