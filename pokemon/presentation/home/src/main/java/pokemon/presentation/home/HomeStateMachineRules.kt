package pokemon.presentation.home

sealed interface HomeState{
    object Loading:HomeState

    data class Loaded(
        val pokemons:List<String>
    ):HomeState

    object Failed:HomeState
}

sealed interface HomeEvent{
    object GetPokemons:HomeEvent
}