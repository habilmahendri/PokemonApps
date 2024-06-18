package pokemon.data.detail.network.remote

import android.util.Log
import pokemon.abstraction.detail.DetailRemoteDataSource
import pokemon.data.base.orZero
import pokemon.data.detail.network.DetailService
import pokemon.model.detail.CatchPokemon
import pokemon.model.detail.Pokemon
import javax.inject.Inject

class DetailRemoteDataSourceImpl @Inject constructor(private val detailService: DetailService) : DetailRemoteDataSource {

    override suspend fun getPokemon(name: String): Pokemon {
        val response = detailService.getPokemon(name)
        val types = arrayListOf<String>()
        val moves = response.moves.take(5).map { it.move.name.capitalize() }.joinToString(separator = ", ", postfix = ".")

        response.types.forEach {
            types.add(it.type.name.orEmpty())
        }

        return Pokemon(name = response.name.orEmpty(), moves = moves, types = types, image  = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${response.id.orEmpty()}.png")
    }

    override suspend fun catchPokemon(): CatchPokemon {
        val response = detailService.catchPokemon()
        return CatchPokemon(probability = response.probability.orZero())
    }
}