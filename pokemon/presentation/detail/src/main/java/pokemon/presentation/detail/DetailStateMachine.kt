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
) : PokemonStateMachine<DetailState, DetailEvent, DetailEffect>() {
    override fun getInitialState(): DetailState {
        return DetailState.Loading
    }

    override fun mapEvent(event: DetailEvent, lastState: DetailState) {

        when (lastState) {
            is DetailState.Loaded -> {
                when(event){
                     is DetailEvent.GetPokemon -> Unit

                    DetailEvent.CatchPokemon -> {
                        launcher.launch(catchPokemonUseCase){
                            state.setValue(lastState.copy(isButtonRelease = true))
                            effect.setValue(DetailEffect.ShowToast(it.probability))
                        }
                    }
                }
            }

            DetailState.Failed -> {}
            DetailState.Loading -> {
                when(event){
                    DetailEvent.CatchPokemon -> Unit
                    is DetailEvent.GetPokemon -> {
                        launcher.launch(getPokemonUseCase,event.name){
                            state.setValue(DetailState.Loaded(it))
                        }
                    }
                }
            }
        }

    }
}