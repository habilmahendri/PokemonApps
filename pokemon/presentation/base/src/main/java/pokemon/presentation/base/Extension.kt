package pokemon.presentation.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import base.presentation.BaseViewModel
import base.presentation.UseCaseLauncher
import kotlinx.coroutines.CoroutineScope
import pokemon.model.base.network.PokemonError

fun BaseViewModel.createLauncher(lmsUseCaseErrorMapper: PokemonUseCaseErrorMapper) =
    object : UseCaseLauncher<PokemonError, PokemonUseCaseErrorMapper> {

        override val scope: CoroutineScope
            get() = viewModelScope

        override fun getErrorMapper(): PokemonUseCaseErrorMapper {
            return lmsUseCaseErrorMapper
        }
    }

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}