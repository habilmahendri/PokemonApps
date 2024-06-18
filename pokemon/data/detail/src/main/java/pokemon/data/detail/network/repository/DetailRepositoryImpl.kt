package pokemon.data.detail.network.repository

import pokemon.abstraction.detail.DetailRemoteDataSource
import pokemon.abstraction.detail.DetailRepository
import pokemon.model.detail.CatchPokemon
import pokemon.model.detail.Pokemon
import pokemon.model.detail.SavePokemon
import pokemon.model.detail.SavePokemonDto
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val detailRemoteDataSource: DetailRemoteDataSource) : DetailRepository {

    override suspend fun getPokemon(name: String): Pokemon {
        return detailRemoteDataSource.getPokemon(name)
    }

    override suspend fun catchPokemon(): CatchPokemon {
        return detailRemoteDataSource.catchPokemon()
    }

    override suspend fun savePokemon(savePokemonDto: SavePokemonDto): SavePokemon {
        return detailRemoteDataSource.savePokemon(savePokemonDto)
    }
}