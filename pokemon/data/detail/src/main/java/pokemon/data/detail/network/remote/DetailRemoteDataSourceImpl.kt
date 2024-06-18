package pokemon.data.detail.network.remote

import pokemon.abstraction.detail.DetailRemoteDataSource
import pokemon.data.base.orFalse
import pokemon.data.base.orZero
import pokemon.data.detail.network.DetailService
import pokemon.model.detail.CatchPokemon
import pokemon.model.detail.Pokemon
import pokemon.model.detail.SavePokemon
import pokemon.model.detail.SavePokemonDto
import javax.inject.Inject

class DetailRemoteDataSourceImpl @Inject constructor(private val detailService: DetailService) : DetailRemoteDataSource {

    override suspend fun getPokemon(name: String): Pokemon {
        val response = detailService.getPokemon(name)
        val types = arrayListOf<String>()
        val moves = response.moves.take(5).map { it.move.name.capitalize() }.joinToString(separator = ", ", postfix = ".")

        response.types.forEach {
            types.add(it.type.name.orEmpty())
        }

        return Pokemon(name = response.name.orEmpty(), moves = moves, types = types, image  = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${response.id.orEmpty()}.png", id = response.id.orEmpty())
    }

    override suspend fun catchPokemon(): CatchPokemon {
        val response = detailService.catchPokemon()
        return CatchPokemon(probability = response.probability.orZero())
    }

    override suspend fun savePokemon(savePokemonDto: SavePokemonDto): SavePokemon {
        val response = detailService.savePokemon(
            savePokemonDto = SavePokemonDto(
                id = savePokemonDto.id,
                name = savePokemonDto.name,
                customName = savePokemonDto.customName
            )
        )
        
        return SavePokemon(success = response.success.orFalse())
    }
}