package pokemon.data.mypokemon.repository

import pokemon.abstraction.mypokemon.MyPokemonRemoteDataSource
import pokemon.abstraction.mypokemon.MyPokemonRepository
import pokemon.model.mypokemon.PokemonItems
import javax.inject.Inject

class MyPokemonRepositoryImpl @Inject constructor(private val myPokemonRemoteDataSource: MyPokemonRemoteDataSource) : MyPokemonRepository {

    override suspend fun getMyPokemons(): List<PokemonItems> {
        return myPokemonRemoteDataSource.getMyPokemons()

    }
}