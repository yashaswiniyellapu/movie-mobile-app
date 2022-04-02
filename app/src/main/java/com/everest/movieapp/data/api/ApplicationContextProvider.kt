package com.everest.movieapp.data.api

import android.app.Application
import android.content.Context

class ApplicationContextProvider : Application() {
    companion object {
        private var instance: ApplicationContextProvider? = null
        fun getInstance(): Context? {
            if (null == instance) {
                instance = ApplicationContextProvider()
            }
            return instance
        }
    }

    init {
        instance = this
    }
}