package pokemon.usecase.home

import base.model.NoArgs
import com.usecase.BaseUseCaseAsync
import pokemon.abstraction.home.HomeRepository
import pokemon.model.home.PokemonItems

class GetPokemonsUseCase(private val repository: HomeRepository) : BaseUseCaseAsync<NoArgs, List<PokemonItems>>() {
    override suspend fun build(args: NoArgs): List<PokemonItems> {
        return repository.getPokemons()
    }

}