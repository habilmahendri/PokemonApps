package pokemon.presentation.mypokemon

import pokemon.model.mypokemon.PokemonItems


sealed interface MyPokemonState{
    object Loading:MyPokemonState

    data class Loaded(
        val pokemons:List<PokemonItems>
    ):MyPokemonState

    object Failed:MyPokemonState
}

sealed interface MyPokemonEvent{
    object GetMyPokemon:MyPokemonEvent
    data class OnPokemonClicked(
        val pokemon: PokemonItems,
    ) : MyPokemonEvent
}

sealed interface MyPokemonEffect {
    data class GoToDetailPokemon(val pokemon: PokemonItems) : MyPokemonEffect
}