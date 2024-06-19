package pokemon.data.mypokemon.network

import retrofit2.http.GET
import retrofit2.http.Url

interface MyPokemonService {

    @GET
    suspend fun getMyPokemonPokemons(
        @Url url:String = "https://pokemon-phincon-test-habil.vercel.app/api/v1/pokemon/list"
    ): MyPokemonResponse
}