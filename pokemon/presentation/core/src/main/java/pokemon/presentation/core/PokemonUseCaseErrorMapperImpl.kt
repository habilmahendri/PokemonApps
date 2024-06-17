package pokemon.presentation.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import pokemon.model.base.network.PokemonApiException
import pokemon.model.base.network.PokemonError
import pokemon.presentation.base.PokemonUseCaseErrorMapper

class PokemonUseCaseErrorMapperImpl : PokemonUseCaseErrorMapper {
    private val _errorEvents = MutableSharedFlow<PokemonError>()
    override val errorEvents: SharedFlow<PokemonError> = _errorEvents

    override fun sendException(scope: CoroutineScope, t: Throwable) {
        scope.launch {
            when (t) {
                is PokemonApiException.NetworkError -> _errorEvents.emit(PokemonError.NetworkError)
                else -> _errorEvents.emit(PokemonError.GeneralError)
            }
        }
    }
}