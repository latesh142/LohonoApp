package com.example.lohonoapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lohonoapp.databinding.ActivityMainBinding
import com.example.lohonoabout.ui.about.AboutFragment
import com.example.lohonoabout.ui.about.AboutData
import com.example.lohonoapp.R
import com.example.lohonotheme.changeTheme.ChangeThemeUtil
import com.example.lohonohome.ui.home.HomeConnector
import com.example.lohonohome.ui.home.HomeFragment
import com.example.lohonolang.changeLanguage.LanguageUtil
import com.example.lohonoprofile.ui.profile.*


/**
 * In this activity we set up the MVVM pattern at low level
 * In future we can expand it to large with same approach
 */
class LohonoActivity : AppCompatActivity(), HomeConnector, ProfileListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var model: LohonoViewModel

    lateinit var homeFragment: HomeFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var aboutFragment: AboutFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        model = ViewModelProvider(this)[LohonoViewModel::class.java]

        setContentView(binding.root)
        setListeners()
        createFragments()
        setInitialFragment()
    }

    /**
     * In this we creating third party fragments
     * HomeFragment -> $LohonoLang.aar
     * ProfileFragment -> $LohonoLang.aar
     * AboutFragment -> $LohonoLang.aar
     */
    private fun createFragments() {
        homeFragment = HomeFragment()
        profileFragment = ProfileFragment(model.getProfile())
        aboutFragment = AboutFragment(model.getAboutData(this))
    }

    /**
     * this will set to the bottom navigation button listener
     * In this method we changing the pages as per user button clicks
     */
    private fun setListeners() {
        binding.bottomNav.setOnItemSelectedListener { it ->
            when (it.itemId) {
                R.id.navigation_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, profileFragment, "Profile").commit()
                }
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, homeFragment, "Home").commit()
                }
                R.id.navigation_about -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, aboutFragment, "About").commit()
                }
            }
            true
        }
    }

    /**
     * We setting home page in center of bottom navigation menu as first page on board
     */
    private fun setInitialFragment() {
        binding.bottomNav.selectedItemId = R.id.navigation_home
        supportFragmentManager.beginTransaction().replace(R.id.frame, homeFragment, "Home").commit()
    }

    override fun onResume() {
        super.onResume()
        homeFragment.setConnector(this)
        profileFragment.setProfileListenerValue(this)
        LanguageUtil.initializeLanguage(intent, this)
    }


    /**
     * this will trigger the theme change to dark
     * which calling change theme library $LohonoTheme.aar
     */
    override fun changeToDark() {
        ChangeThemeUtil.setThemeMode(ChangeThemeUtil.DARK)
        resetLanguageInitialization()
    }

    /**
     * this will trigger the theme change to light
     * which is calling change theme library $LohonoTheme.aar
     */
    override fun changeToLight() {
        ChangeThemeUtil.setThemeMode(ChangeThemeUtil.LIGHT)
        resetLanguageInitialization()
    }

    /**
     * This function is important while performing any method which leads to restart the activity
     * example change theme function
     */
    private fun resetLanguageInitialization(){
        intent.putExtra(LanguageUtil.INITIALIZATION, false)
    }

    /**
     * THis will trigger the language change at application level
     * this is using the change language library $LohonoLang.aar
     */
    override fun onLanguageChange(language: String) {
        when (language) {
            ProfileConstants.ENGLISH -> LanguageUtil.setLocale(LanguageUtil.ENGLISH, intent, this)
            ProfileConstants.HINDI -> LanguageUtil.setLocale(LanguageUtil.HINDI, intent, this)
        }
    }
}