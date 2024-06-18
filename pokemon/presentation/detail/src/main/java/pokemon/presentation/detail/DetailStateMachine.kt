package pokemon.presentation.detail

import base.model.NoArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import pokemon.model.detail.SavePokemonDto
import pokemon.presentation.base.PokemonStateMachine
import pokemon.presentation.base.PokemonUseCaseErrorMapper
import pokemon.usecase.detail.CatchPokemonUseCase
import pokemon.usecase.detail.GetDetailPokemonUseCase
import pokemon.usecase.detail.SavePokemonUseCase
import javax.inject.Inject


@HiltViewModel
class DetailStateMachine @Inject constructor(private val getPokemonUseCase: GetDetailPokemonUseCase,
                                             private val catchPokemonUseCase: CatchPokemonUseCase,
                                             private val savePokemonUseCase: SavePokemonUseCase,
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
                            if (it.probability == 1) {
                                state.setValue(lastState.copy(isSuccessCatchPokemon = true))
                            }
                            effect.setValue(DetailEffect.ShowToast(it.probability))
                        }
                    }

                    is DetailEvent.SavePokemon ->{
                        launcher.launch(savePokemonUseCase, event.savePokemonDto){
                            state.setValue(lastState.copy(isSuccessCatchPokemon = true))
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

                    is DetailEvent.SavePokemon -> Unit
                }
            }
        }

    }
}