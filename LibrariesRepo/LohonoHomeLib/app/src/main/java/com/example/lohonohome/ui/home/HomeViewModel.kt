package com.example.lohonohome.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * this is small example of MVVM
 * in future we can expand it with same approach
 */
class HomeViewModel : ViewModel() {

    val themeMode: MutableLiveData<String> = MutableLiveData<String>()

    open fun setThemeMode(mode: String) {
        themeMode.value = mode
    }
}