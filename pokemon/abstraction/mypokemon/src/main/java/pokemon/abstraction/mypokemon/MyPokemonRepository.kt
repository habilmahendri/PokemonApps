package pokemon.abstraction.mypokemon

import pokemon.model.mypokemon.PokemonItems

interface MyPokemonRepository {
    suspend fun getMyPokemons(): List<PokemonItems>
}