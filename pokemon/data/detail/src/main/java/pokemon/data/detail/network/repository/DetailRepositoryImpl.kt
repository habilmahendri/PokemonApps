package pokemon.data.detail.network.repository

import android.util.Log
import pokemon.abstraction.detail.DetailRemoteDataSource
import pokemon.abstraction.detail.DetailRepository
import pokemon.model.detail.CatchPokemon
import pokemon.model.detail.Pokemon
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val detailRemoteDataSource: DetailRemoteDataSource) : DetailRepository {

    override suspend fun getPokemon(name: String): Pokemon {
        Log.d("getpokemon",name)
        return detailRemoteDataSource.getPokemon(name)
    }

    override suspend fun catchPokemon(): CatchPokemon {
        return detailRemoteDataSource.catchPokemon()
    }
}