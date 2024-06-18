package pokemon.abstraction.detail

import pokemon.model.detail.CatchPokemon
import pokemon.model.detail.Pokemon

interface DetailRemoteDataSource  {
    suspend fun getPokemon(name:String): Pokemon
    suspend fun catchPokemon():CatchPokemon
}