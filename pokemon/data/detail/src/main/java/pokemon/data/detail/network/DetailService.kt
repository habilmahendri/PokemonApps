package pokemon.data.detail.network

import pokemon.data.detail.network.response.CatchPokemonResponse
import pokemon.data.detail.network.response.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface DetailService {
    @GET("v2/pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
        ): DetailResponse

    @GET
    suspend fun catchPokemon(
        @Url url:String = "https://pokemon-phincon-test-habil.vercel.app/api/v1/pokemon/catch"
    ): CatchPokemonResponse

}