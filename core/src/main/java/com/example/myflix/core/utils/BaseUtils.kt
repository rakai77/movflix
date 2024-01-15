package com.example.myflix.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

fun <T> execute(
    coroutineContext: CoroutineContext,
    block: suspend () -> T
) : Flow<T> {
    return flow {
        val out = block.invoke()
        emit(out)
    }.flowOn(coroutineContext)
}