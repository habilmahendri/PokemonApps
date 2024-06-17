package pokemon.abstraction.home

interface HomeRemoteDataSource  {
    suspend fun getPokemons(): List<String>
}