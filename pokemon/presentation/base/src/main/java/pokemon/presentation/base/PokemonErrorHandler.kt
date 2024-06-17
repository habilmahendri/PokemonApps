package pokemon.presentation.base

import base.presentation.DefaultErrorHandler
import pokemon.model.base.network.PokemonError

interface PokemonErrorHandler : DefaultErrorHandler<PokemonError> {
    fun listenEvent(callback: (Unit) -> Unit)
}
