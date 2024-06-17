package com.usecase

import base.model.UseCaseState
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.*


abstract class BaseUseCaseFlow<in Args, Result> : BaseUseCase() {
    protected abstract fun build(args: Args): Flow<Result>

    open val maxRetries = 0

    private val _state = MutableStateFlow<UseCaseState<Flow<Result>>>(UseCaseState.Initial)
    val state : StateFlow<UseCaseState<Flow<Result>>> = _state

    operator fun invoke(args: Args): Flow<Result> {
        _state.value = UseCaseState.Initial
        _state.value = UseCaseState.Executing(0)
        return flow {
            withExecutionTimeout {
                val result  = build(args)
                emitAll(result)
                _state.value = UseCaseState.Success(result)
            }
        }.retryWhen { cause, attempt ->
            _state.value = UseCaseState.Executing(attempt.toInt())
            attempt < maxRetries.toLong() && cause is TimeoutCancellationException
        }.flowOn(coroutineContext).catch {
            if (it !is TimeoutCancellationException){
                _state.value = UseCaseState.Failed(it)
                throw it
            }
        }
    }
}