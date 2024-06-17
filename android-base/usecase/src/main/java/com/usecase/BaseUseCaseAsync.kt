package com.usecase

import base.model.UseCaseState
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

abstract class BaseUseCaseAsync<in Args, Result> : BaseUseCase() {
    protected abstract suspend fun build(args: Args): Result
    private val _state = MutableStateFlow<UseCaseState<Result>>(UseCaseState.Initial)
    val state: StateFlow<UseCaseState<Result>> = _state
    open val maxRetries = 3
    suspend operator fun invoke(args: Args): Result {
        _state.value = UseCaseState.Initial
        repeat(maxRetries - 1) {
            _state.value = UseCaseState.Executing(it)
            try {
                return invokeOperation(args).also {
                    _state.value = UseCaseState.Success(it)
                }
            } catch (e: Exception) {
                if (e !is TimeoutCancellationException) {
                    _state.value = UseCaseState.Failed(e)
                    throw e
                }
            }
        }
        _state.value = UseCaseState.Executing(maxRetries - 1)
        return try {
            invokeOperation(args).also {
                _state.value = UseCaseState.Success(it)
            }
        } catch (e: Exception) {
            _state.value = UseCaseState.Failed(e)
            throw e
        }
    }

    private suspend fun invokeOperation(args: Args): Result {
        return withContext(coroutineContext) {
            withExecutionTimeout {
                build(args)
            }
        }
    }
}