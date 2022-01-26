package com.toledorobia.cariocascore.core

import android.util.Log
import javax.inject.Inject

private const val LOG_TAG = "com.toledorobia.LOG_TAG"

class Logger @Inject constructor() {

    fun d(value: Any?) {
        Log.d(LOG_TAG, value.toString())
    }

}