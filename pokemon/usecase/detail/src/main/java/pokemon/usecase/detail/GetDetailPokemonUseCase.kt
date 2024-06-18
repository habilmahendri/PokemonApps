package pokemon.usecase.detail

import base.model.NoArgs
import com.usecase.BaseUseCaseAsync
import pokemon.abstraction.detail.DetailRepository
import pokemon.model.detail.Pokemon

class GetDetailPokemonUseCase(private val repository: DetailRepository) : BaseUseCaseAsync<String, Pokemon>() {
    override suspend fun build(args: String): Pokemon {
        return repository.getPokemon(args)
    }

}