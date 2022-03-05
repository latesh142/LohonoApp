package com.example.lohonoapp.application

import android.app.Application

/**
 * this will provide the application context for top level configuration
 * this is not in use but we can use it for future
 * but sometime necessary to have while implementing large scale app
 */
open class LohonoApplication: Application() {

    companion object{
        lateinit var application: LohonoApplication
        fun getInstance(): LohonoApplication{
            return application
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}