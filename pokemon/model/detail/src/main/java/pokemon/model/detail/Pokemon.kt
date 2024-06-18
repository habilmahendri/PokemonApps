package pokemon.model.detail

data class Pokemon(
    val name:String,
    val image:String,
    val moves:String,
    val types:List<String>,
    val id:String
)
