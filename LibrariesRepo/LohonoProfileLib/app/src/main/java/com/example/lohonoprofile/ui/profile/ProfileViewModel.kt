package com.example.lohonoprofile.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * this will help to assing data and it will reflect to the ui also
 * by settled observers
 */
class ProfileViewModel : ViewModel() {

    val userName: MutableLiveData<String> = MutableLiveData<String>()
    val area: MutableLiveData<String> = MutableLiveData<String>()
    val contact: MutableLiveData<String> = MutableLiveData<String>()

    fun setUserName(title: String){
        userName.value = title
    }

    fun serAreaName(areaTitle: String){
        area.value = areaTitle
    }

    fun setContact(contactNumber: String){
        contact.value = contactNumber
    }
}