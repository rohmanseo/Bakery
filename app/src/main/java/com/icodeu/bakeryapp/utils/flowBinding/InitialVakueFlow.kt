package com.icodeu.bakeryapp.utils.flowBinding

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.onStart


//This code from : https://github.com/ReactiveCircus/FlowBinding/blob/main/flowbinding-common/src/main/java/reactivecircus/flowbinding/common/InitialValueFlow.kt

/**
 * Converts a [Flow] to an [InitialValueFlow], taking an [initialValue] lambda for computing the initial value.
 */
fun <T : Any> Flow<T>.asInitialValueFlow(initialValue: () -> T): InitialValueFlow<T> = InitialValueFlow(
    onStart {
        emit(initialValue())
    }
)

/**
 * A [Flow] implementation that emits the current value of a widget immediately upon collection.
 */
class InitialValueFlow<T : Any>(private val flow: Flow<T>) : Flow<T> by flow {

    /**
     * Returns a [Flow] that skips the initial emission of the current value.
     */
    fun skipInitialValue(): Flow<T> = flow.drop(1)
}