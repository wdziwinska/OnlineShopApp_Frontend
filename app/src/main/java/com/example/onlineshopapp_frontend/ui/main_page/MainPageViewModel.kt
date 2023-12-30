package com.example.onlineshopapp_frontend.ui.main_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainPageViewModel : ViewModel() {

    private val _texts = MutableLiveData<List<String>>().apply {
        value = (1..16).mapIndexed { _, i ->
            "Produkt # $i"
        }
    }

    val texts: LiveData<List<String>> = _texts
}