package pokemon.presentation.home

import pokemon.model.home.PokemonItems

sealed interface HomeState{
    object Loading:HomeState

    data class Loaded(
        val pokemons:List<PokemonItems>
    ):HomeState

    object Failed:HomeState
}

sealed interface HomeEvent{
    object GetPokemons:HomeEvent
    object OnMyPokemonClicked:HomeEvent
    data class OnPokemonClicked(
        val pokemon: PokemonItems,
    ) : HomeEvent
}

sealed interface HomeEffect {
    data class GoToDetailPokemon(val pokemon: PokemonItems) : HomeEffect
    object GotoMyPokemon : HomeEffect
}