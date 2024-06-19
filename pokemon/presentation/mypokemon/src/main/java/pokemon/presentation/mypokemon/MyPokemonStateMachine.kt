package pokemon.presentation.mypokemon

import dagger.hilt.android.lifecycle.HiltViewModel
import pokemon.presentation.base.PokemonStateMachine
import pokemon.presentation.base.PokemonUseCaseErrorMapper
import pokemon.usecase.mypokemon.GetMyPokemonsUseCase
import javax.inject.Inject

@HiltViewModel
class MyPokemonStateMachine @Inject constructor(private val getMyPokemonsUseCase: GetMyPokemonsUseCase,
                                                override var errorMapper: PokemonUseCaseErrorMapper
) : PokemonStateMachine<MyPokemonState, MyPokemonEvent,MyPokemonEffect>() {
    override fun getInitialState(): MyPokemonState {
        return MyPokemonState.Loading
    }

    override fun mapEvent(event: MyPokemonEvent, lastState: MyPokemonState) {
        when (lastState) {

            is MyPokemonState.Loaded -> {
                when(event){
                    MyPokemonEvent.GetMyPokemon -> {
                        launcher.launch(getMyPokemonsUseCase){
                            state.setValue(MyPokemonState.Loaded(it))
                        }
                    }
                    is MyPokemonEvent.OnPokemonClicked ->{
                        effect.setValue(MyPokemonEffect.GoToDetailPokemon(event.pokemon))
                    }
                }
            }
            MyPokemonState.Loading -> {
                when (event) {
                    MyPokemonEvent.GetMyPokemon -> {
                        launcher.launch(getMyPokemonsUseCase){
                            state.setValue(MyPokemonState.Loaded(it))
                        }
                    }

                    else -> {}
                }
            }

            else -> {}
        }
    }
}