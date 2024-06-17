package base.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> createState(initialValue: T): StateFlow<T> = MutableStateFlow(initialValue)
    fun <T> StateFlow<T>.setValue(state: T, reset : T?= null) {
        if (this is MutableStateFlow) {
            Log.d("STATE_MACHINE", "state = $state")
            this.value = state
        }
    }

    protected fun <T> StateFlow<T>.updateState(function: (T) -> T) {
        if (this is MutableStateFlow) {
            this.update(function)
        }
    }

    protected fun <T> createSharedFlow(): MutableSharedFlow<T> = MutableSharedFlow<T>().apply {
        shareIn(viewModelScope, SharingStarted.WhileSubscribed())
    }

    fun <T> SharedFlow<T>.setValue(data : T){
        if (this is MutableSharedFlow){
            viewModelScope.launch {
                Log.d("STATE_MACHINE", "effect = $data")
                emit(data)
            }
        }

    }

}