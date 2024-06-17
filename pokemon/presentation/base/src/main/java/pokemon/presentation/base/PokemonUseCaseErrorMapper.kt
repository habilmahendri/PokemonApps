package pokemon.presentation.base

import base.presentation.UseCaseErrorMapper
import pokemon.model.base.network.PokemonError

interface PokemonUseCaseErrorMapper : UseCaseErrorMapper<PokemonError>
