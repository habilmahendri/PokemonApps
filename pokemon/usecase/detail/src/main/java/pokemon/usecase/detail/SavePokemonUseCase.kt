package pokemon.usecase.detail

import com.usecase.BaseUseCaseAsync
import pokemon.abstraction.detail.DetailRepository
import pokemon.model.detail.SavePokemon
import pokemon.model.detail.SavePokemonDto

class SavePokemonUseCase(private val repository: DetailRepository) : BaseUseCaseAsync<SavePokemonDto, SavePokemon>() {

    override suspend fun build(args: SavePokemonDto): SavePokemon {
        return repository.savePokemon(args)
    }

}