package pokemon.abstraction.home

interface HomeRepository {
    suspend fun getPokemons(): List<String>
}