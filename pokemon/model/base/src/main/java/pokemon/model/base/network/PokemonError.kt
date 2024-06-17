package pokemon.model.base.network

sealed class PokemonError {
    object GeneralError : PokemonError()
    object NetworkError : PokemonError()
}
