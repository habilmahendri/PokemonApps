package pokemon.presentation.home

import base.model.NoArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import pokemon.presentation.base.PokemonStateMachine
import pokemon.presentation.base.PokemonUseCaseErrorMapper
import pokemon.usecase.home.GetPokemonsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeStateMachine @Inject constructor(private val getPokemonsUseCase: GetPokemonsUseCase,
                                           override var errorMapper: PokemonUseCaseErrorMapper
) : PokemonStateMachine<HomeState,HomeEvent,HomeEffect>() {
    override fun getInitialState(): HomeState {
        return HomeState.Loading
    }

    override fun mapEvent(event: HomeEvent, lastState: HomeState) {

        when (lastState) {
            is HomeState.Loaded -> {
                when(event){
                    HomeEvent.GetPokemons -> {
                        launcher.launch(getPokemonsUseCase){
                            state.setValue(HomeState.Loaded(it))
                        }
                    }
                    is HomeEvent.OnPokemonClicked ->{
                        effect.setValue(HomeEffect.GoToDetailPokemon(event.pokemon))
                    }
                    HomeEvent.OnMyPokemonClicked -> {
                        effect.setValue(HomeEffect.GotoMyPokemon)
                    }
                }
            }

            HomeState.Loading -> {
                when (event) {
                    HomeEvent.GetPokemons -> {
                        launcher.launch(getPokemonsUseCase){
                            state.setValue(HomeState.Loaded(it))
                        }
                    }

                    else -> {}
                }
            }
            else -> {}
        }

    }
}