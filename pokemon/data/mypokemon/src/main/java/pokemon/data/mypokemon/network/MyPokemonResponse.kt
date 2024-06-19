package pokemon.data.mypokemon.network

data class MyPokemonResponse(
    val data:List<Pokemon>?
){
    data class Pokemon(
        val id:String?,
        val name:String?,
        val customName:String?
    )
}