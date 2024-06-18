package pokemon.data.base

fun getIdFromPokemonUrl(urlString: String): Int? {
    val stripped = urlString.dropLast(1)
    val id = stripped.split("/").last().toIntOrNull()
    var output: Int? = null
    if (id != null) {
        output = id
    } else {
        // Invalid URL or ID not found
    }
    return output
}


fun Int?.orZero(): Int {
    return this ?: 0
}