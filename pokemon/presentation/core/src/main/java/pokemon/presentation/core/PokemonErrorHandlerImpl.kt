package pokemon.presentation.core

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pokemon.model.base.Callback
import pokemon.model.base.network.GenericErrorModel
import pokemon.model.base.network.PokemonError
import pokemon.presentation.base.PokemonErrorHandler
import pokemon.presentation.base.PokemonUseCaseErrorMapper
import pokemon.presentation.base.toast

class PokemonErrorHandlerImpl (
    override val activity: FragmentActivity,
    private val errorMapper: PokemonUseCaseErrorMapper,
) : PokemonErrorHandler {

    init {
        with(activity) {
            lifecycleScope.launchWhenStarted {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    errorMapper.errorEvents.onEach {
                        onErrorAction(it)
                    }.launchIn(this)
                }
            }
        }
    }

    private var eventCallback: Callback<Unit, Unit>? = null

    override fun onErrorAction(error: PokemonError) {
        when (error) {
            PokemonError.GeneralError -> {
                val model = GenericErrorModel(
                    field = "Whoops!",
                    message = "Something went wrong",
                    primaryLink = "OK",
                )
                showErrorSheet(model)
            }

            PokemonError.NetworkError -> {
                val model = GenericErrorModel(
                    field = "Whoops!",
                    message = "Please check your internet connection",
                    primaryText = "OK",
                )
                showErrorSheet(model)
            }
        }
    }

    private fun showErrorSheet(model: GenericErrorModel) {
        activity.toast(model.message)
    }

    override fun listenEvent(callback: (Unit) -> Unit) {
        eventCallback = callback
    }
}