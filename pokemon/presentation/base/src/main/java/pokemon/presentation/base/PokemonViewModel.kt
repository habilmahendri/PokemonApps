package pokemon.presentation.base

import androidx.lifecycle.viewModelScope
import base.presentation.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn


abstract class PokemonViewModel : BaseViewModel() {

    abstract var errorMapper: PokemonUseCaseErrorMapper

    val errorEvents by lazy {
        errorMapper.errorEvents.apply {
            shareIn(viewModelScope, SharingStarted.WhileSubscribed())
        }
    }

    val launcher by lazy { createLauncher(errorMapper) }
}
