package pokemon.data.mypokemon.remote

import pokemon.abstraction.mypokemon.MyPokemonRemoteDataSource
import pokemon.data.base.getIdFromPokemonUrl
import pokemon.data.mypokemon.network.MyPokemonService
import pokemon.model.mypokemon.PokemonItems
import javax.inject.Inject

class MyPokemonRemoteDataSourceImpl @Inject constructor(private val myPokemonService: MyPokemonService) : MyPokemonRemoteDataSource {

    override suspend fun getMyPokemons(): List<PokemonItems> {
        val response = myPokemonService.getMyPokemonPokemons()
        val result = response.data
        return result?.map {
            PokemonItems(
                name = it.name.orEmpty(), id = it.id.orEmpty(), customName = it.customName.orEmpty(), url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${it.id}.png"

            )
        } ?: listOf()
    }
}