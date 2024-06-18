package pokemon.presentation.base

import android.os.Bundle
import androidx.annotation.CallSuper
import base.presentation.BaseActivity
import javax.inject.Inject

abstract class PokemonActivity : BaseActivity() {

    @Inject
    lateinit var errorHandler: PokemonErrorHandler

    @Inject
    lateinit var errorMapper: PokemonUseCaseErrorMapper

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorHandler.listenEvent(::errorHandlerCallback)
    }

    protected open fun errorHandlerCallback(events: Unit) {}
}