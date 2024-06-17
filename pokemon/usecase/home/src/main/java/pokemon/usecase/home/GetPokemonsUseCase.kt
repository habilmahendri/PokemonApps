package pokemon.usecase.home

import base.model.NoArgs
import com.usecase.BaseUseCaseAsync
import pokemon.abstraction.home.HomeRepository

class GetPokemonsUseCase(private val repository: HomeRepository) : BaseUseCaseAsync<NoArgs, List<String>>() {
    override suspend fun build(args: NoArgs): List<String> {
        return repository.getPokemons()
    }

}