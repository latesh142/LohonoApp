package com.example.lohonolang.changeLanguage

import android.app.Activity
import android.content.Intent
import java.util.*
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences

/**
 * this is singleton pattern for access this library quickly
 * also this class can hold the constants for library
 */
object LanguageUtil {
    open val ENGLISH = "ENGLISH"
    open val HINDI = "HINDI"
    val LANGUAGE = "LANGUAGE"
    val INITIALIZATION = "INITIALIZATION"

    /**
     * this method will be available outside package also
     * for quick access and simple vision we make it directly available
     * without extra encapsulation
     */
    open fun setLocale(languageCode: String, intent: Intent, activity: Activity): Boolean {
        val langCode = getLangCode(languageCode)
        val existCode = existingLangCode(activity)

        if (langCode != existCode) {
            changeAndRestart(languageCode, intent, activity)
            return true
        }
        return false
    }

    private fun changeAndRestart(languageCode: String, intent: Intent, activity: Activity) {
        var locale = Locale(getLangCode(languageCode))
        val res = activity.resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = locale
        res.updateConfiguration(conf, dm)
        saveLangData(languageCode, activity)
        activity.finish()
        activity.startActivity(intent)
    }

    private fun saveLangData(lang: String, activity: Activity) {
        val editor: SharedPreferences.Editor =
            activity.getSharedPreferences(LANGUAGE, MODE_PRIVATE).edit()
        editor.putString(LANGUAGE, lang)
        editor.putInt("idName", 12)
        editor.apply()
    }

    private fun existingLangCode(activity: Activity): String {
        val prefs: SharedPreferences = activity.getSharedPreferences(LANGUAGE, MODE_PRIVATE)
        return prefs.getString(LANGUAGE, ENGLISH)!!
    }

    /**
     * this method will help you to set predefined language
     */
    open fun initializeLanguage(intent: Intent, activity: Activity) {
        val initializationCheck = intent.getBooleanExtra(INITIALIZATION, false)

        if(!initializationCheck){
            val code = existingLangCode(activity)
            intent.putExtra(INITIALIZATION, true)
            changeAndRestart(code, intent, activity)
        }
    }

    private fun getLangCode(lang: String): String {
        return when (lang) {
            ENGLISH -> "en"
            HINDI -> "hi"
            else -> "en"
        }
    }
}