package pokemon.abstraction.mypokemon

import pokemon.model.mypokemon.PokemonItems

interface MyPokemonRemoteDataSource {
    suspend fun getMyPokemons(): List<PokemonItems>
}