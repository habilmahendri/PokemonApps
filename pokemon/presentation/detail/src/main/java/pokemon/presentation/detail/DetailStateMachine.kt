package pokemon.presentation.detail

import base.model.NoArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import pokemon.presentation.base.PokemonStateMachine
import pokemon.presentation.base.PokemonUseCaseErrorMapper
import pokemon.usecase.detail.CatchPokemonUseCase
import pokemon.usecase.detail.GetDetailPokemonUseCase
import javax.inject.Inject


@HiltViewModel
class DetailStateMachine @Inject constructor(private val getPokemonUseCase: GetDetailPokemonUseCase,
                                             private val catchPokemonUseCase: CatchPokemonUseCase,
                                           override var errorMapper: PokemonUseCaseErrorMapper
) : PokemonStateMachine<DetailState, DetailEvent, NoArgs>() {
    override fun getInitialState(): DetailState {
        return DetailState.Loading
    }

    override fun mapEvent(event: DetailEvent, lastState: DetailState) {

        when (lastState) {
            is DetailState.Loaded -> {
                when(event){
                     is DetailEvent.GetPokemon -> {
                        launcher.launch(getPokemonUseCase,event.name){
                            state.setValue(DetailState.Loaded(it))
                        }
                    }

                    DetailEvent.CatchPokemon -> {
                        launcher.launch(catchPokemonUseCase){
                            state.setValue(DetailState.CatchedPokemon(it))
                        }
                    }
                }
            }
            else -> {}
        }

    }
}