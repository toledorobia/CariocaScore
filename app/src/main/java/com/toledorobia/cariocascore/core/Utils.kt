package com.toledorobia.cariocascore.core

import android.content.Context
import com.toledorobia.cariocascore.R
import java.text.DateFormat
import java.util.*

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

    fun defaultGameName(): String {
        //val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
        val format: DateFormat = DateFormat.getDateTimeInstance(
            DateFormat.DEFAULT,
            DateFormat.MEDIUM,
            Locale.getDefault()
        )

        return str(R.string.game_default_name, format.format(Date()))
    }
}