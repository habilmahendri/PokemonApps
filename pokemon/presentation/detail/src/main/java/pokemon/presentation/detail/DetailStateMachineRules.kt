package pokemon.presentation.detail

import pokemon.model.detail.CatchPokemon
import pokemon.model.detail.Pokemon
import pokemon.model.detail.SavePokemonDto

sealed interface DetailState{
    object Loading:DetailState

    data class Loaded(
        val pokemon: Pokemon,
        val isSuccessCatchPokemon:Boolean = false
    ):DetailState

    object Failed:DetailState
}

sealed interface DetailEvent{
    data class GetPokemon(val name:String):DetailEvent
    data class SavePokemon(val savePokemonDto: SavePokemonDto):DetailEvent
    object CatchPokemon:DetailEvent
}

sealed interface DetailEffect{
    data class ShowToast(val probability:Int):DetailEffect
}
