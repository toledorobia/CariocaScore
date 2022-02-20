package com.toledorobia.cariocascore.core

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Utils (private val appContext: Context) {
    fun getStringByName(value: String): String {
        val id = appContext.resources.getIdentifier(value, "string", appContext.packageName)
        return appContext.getString(id)
    }

    fun getString(id: Int, vararg values: Any): String {
        return appContext.getString(id, *values)
    }

    fun str(id: Int, vararg values: Any): String {
        return getString(id, *values)
    }
}