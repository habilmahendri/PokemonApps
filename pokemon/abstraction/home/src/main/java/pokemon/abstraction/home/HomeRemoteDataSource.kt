package pokemon.abstraction.home

import pokemon.model.home.PokemonItems

interface HomeRemoteDataSource  {
    suspend fun getPokemons(): List<PokemonItems>
}