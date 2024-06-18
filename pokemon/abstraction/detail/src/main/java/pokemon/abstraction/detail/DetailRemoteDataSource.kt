package pokemon.abstraction.detail

import pokemon.model.detail.CatchPokemon
import pokemon.model.detail.Pokemon
import pokemon.model.detail.SavePokemon
import pokemon.model.detail.SavePokemonDto

interface DetailRemoteDataSource  {
    suspend fun getPokemon(name:String): Pokemon
    suspend fun catchPokemon():CatchPokemon
    suspend fun savePokemon(savePokemonDto: SavePokemonDto):SavePokemon
}