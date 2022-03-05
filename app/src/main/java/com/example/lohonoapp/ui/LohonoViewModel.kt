package com.example.lohonoapp.ui

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.lohonoabout.ui.about.AboutData
import com.example.lohonoapp.R
import com.example.lohonoapp.application.LohonoApplication
import com.example.lohonoprofile.ui.profile.Profile

/**
 * For now we set up with offline data
 * In future we can expand it to use data over the api services and
 * handle data with live data observers
 */
class LohonoViewModel: ViewModel() {

    /**
     * Here you can set up data for profile library
     */
    fun getProfile(): Profile {
        return Profile(
            "Latesh Mumbarkar",
            "Mumbai 400001",
            "+91 8793620164 \n latesh.mumbarkar@gmail.com",
            R.drawable.dummy_profile
        )
    }

    /**
     * Here you can set up data for about us page
     */
    fun getAboutData(context: Context): AboutData {
        return AboutData(
            context.getString(R.string.about_title),
            context.getString(R.string.about_desc),
            R.drawable.download
        )
    }
}