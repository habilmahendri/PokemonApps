package pokemon.usecase.detail

import base.model.NoArgs
import com.usecase.BaseUseCaseAsync
import pokemon.abstraction.detail.DetailRepository
import pokemon.model.detail.CatchPokemon
import pokemon.model.detail.Pokemon

class CatchPokemonUseCase(private val repository: DetailRepository) : BaseUseCaseAsync<NoArgs, CatchPokemon>() {
    override suspend fun build(args: NoArgs): CatchPokemon {
        return repository.catchPokemon()
    }

}