package pokemon.abstraction.home

import pokemon.model.home.PokemonItems

interface HomeRepository {
    suspend fun getPokemons(): List<PokemonItems>
}