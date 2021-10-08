package com.icodeu.bakeryapp.presentation.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.domain.model.Bread
import com.icodeu.bakeryapp.domain.use_case.bread.GetSimilarBreadUseCase
import com.icodeu.bakeryapp.core.utils.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class ItemViewModel(
    private val getSimilarBreadUseCase: GetSimilarBreadUseCase,
) : ViewModel() {

    private val _similar = MutableSharedFlow<Resource<List<Bread>>>(1)
    val similar: SharedFlow<Resource<List<Bread>>>
        get() = _similar

    init {
        getSimilar()
    }

    fun getSimilar() {
        viewModelScope.launch {
            getSimilarBreadUseCase().collect {
                _similar.emit(it)
            }
        }
    }

}