package com.example.lohonoabout.ui.about

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * this is small example for observer data(Live data)
 * we can expand it for large application with same approach
 */
class AboutViewModel : ViewModel() {

    val aboutUsTitle: MutableLiveData<String> = MutableLiveData<String>()
    val description: MutableLiveData<String> = MutableLiveData<String>()

    fun setAboutUsTitle(title: String){
        aboutUsTitle.value = title
    }

    fun setDescription(desc: String){
        description.value = desc
    }
}