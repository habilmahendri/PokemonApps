package pokemon.data.home.network

import pokemon.data.base.BaseResponse
import pokemon.data.home.network.model.response.PokemonResponse
import retrofit2.http.GET

interface HomeService {
    @GET("v2/pokemon/")
    suspend fun getPokemons(): BaseResponse<List<PokemonResponse>>

}