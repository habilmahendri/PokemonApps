package pokemon.data.detail.network.response

data class DetailResponse(
    val name:String?,
    val id:String?,
    val types:List<Types>,
    val moves:List<Moves>
){
    data class Types(
        val type:Type
    ){
        data class Type(
            val name:String?
        )
    }
    data class Moves(
        val move:Move
    ){
        data class Move(
            val name:String
        )
    }
}
