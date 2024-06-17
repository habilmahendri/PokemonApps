package pokemon.model.base.network

import java.io.IOException

sealed class PokemonApiException : IOException() {
    object SystemError : PokemonApiException()
    object NetworkError : PokemonApiException()
}
