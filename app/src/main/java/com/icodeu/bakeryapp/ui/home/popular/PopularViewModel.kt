package com.icodeu.bakeryapp.ui.home.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icodeu.bakeryapp.ui.home.Cake

class PopularViewModel : ViewModel() {

    private val _popular = MutableLiveData<List<Cake>>()
    var popular: LiveData<List<Cake>> = _popular
        private set

    fun populars(): LiveData<List<Cake>> {
        return popular
    }

    fun getPopular() {
        val names = listOf<String>("Cinnamon", "Something", "Then", "Anything")
        val cakes = mutableListOf<Cake>()
        names.forEach {
            cakes.add(Cake(it.hashCode(), name = it))
        }
        _popular.postValue(cakes)

    }

}