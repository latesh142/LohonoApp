package com.example.lohonotheme.changeTheme

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate

/**
 * this is singleton class, we keep it open to make easy access
 */
object ChangeThemeUtil {
    open val DARK = "DARK"
    open val LIGHT = "LIGHT"

    /**
     * this will used to check the current theme mode
     * this can be use for future purpose
     */
    open fun getCurrentTheme(context: Context): String {
        val nightModeFlags = context!!.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK

        return when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> DARK
            Configuration.UI_MODE_NIGHT_NO -> LIGHT
            Configuration.UI_MODE_NIGHT_UNDEFINED -> DARK
            else -> DARK
        }
    }

    /**
     * this will set the theme mode as request by user
     * for now to understand we keep it simple without any further abstraction
     */
    open fun setThemeMode(theme: String){
        when (theme) {
            DARK -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            LIGHT -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}