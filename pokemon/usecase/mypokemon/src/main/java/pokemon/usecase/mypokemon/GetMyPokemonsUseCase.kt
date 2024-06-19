package pokemon.usecase.mypokemon

import base.model.NoArgs
import com.usecase.BaseUseCaseAsync
import pokemon.abstraction.mypokemon.MyPokemonRepository
import pokemon.model.mypokemon.PokemonItems

class GetMyPokemonsUseCase(private val repository: MyPokemonRepository) : BaseUseCaseAsync<NoArgs, List<PokemonItems>>() {
    override suspend fun build(args: NoArgs): List<PokemonItems> {
        return repository.getMyPokemons()
    }

}