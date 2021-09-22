package com.icodeu.bakeryapp.ui.home.recommended

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icodeu.bakeryapp.ui.home.Cake

class RecommendedViewModel : ViewModel() {

    private val _recommeded = MutableLiveData<List<Cake>>()
    var recommeded: LiveData<List<Cake>> = _recommeded
        private set


    fun getRecommeds() {
        val names = listOf<String>(
            "Cinnamon",
            "Something",
            "Then",
            "Anything",
        )
        val cakes = mutableListOf<Cake>()
        names.forEach {
            cakes.add(Cake(it.hashCode(), name = it))
        }
        _recommeded.postValue(cakes)

    }

}