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
) : PokemonStateMachine<HomeState,HomeEvent,NoArgs>() {
    override fun getInitialState(): HomeState {
        return HomeState.Loading
    }

    override fun mapEvent(event: HomeEvent, lastState: HomeState) {
        launcher.launch(getPokemonsUseCase){
            state.setValue(HomeState.Loaded(it))
        }
    }
}