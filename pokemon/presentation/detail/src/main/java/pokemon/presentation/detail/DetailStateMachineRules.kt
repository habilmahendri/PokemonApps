package pokemon.presentation.detail

import pokemon.model.detail.CatchPokemon
import pokemon.model.detail.Pokemon

sealed interface DetailState{
    object Loading:DetailState

    data class Loaded(
        val pokemon: Pokemon
    ):DetailState

    data class CatchedPokemon(
        val catchPokemon: CatchPokemon
    ):DetailState

    object Failed:DetailState
}

sealed interface DetailEvent{
    data class GetPokemon(val name:String):DetailEvent
    object CatchPokemon:DetailEvent
}

sealed interface DetailEffect{

}
