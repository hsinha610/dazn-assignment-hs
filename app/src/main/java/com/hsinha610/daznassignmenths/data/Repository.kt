package com.hsinha610.daznassignmenths.data

import android.app.Application
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class Repository @Inject constructor(private val application: Application, private val gson: Gson) {

    suspend fun getData(): DataList {
        return withContext(Dispatchers.Default) {
            val list =
                gson.fromJson(getJsonString(), Array<DataListItem>::class.java)
                    .toList()
            return@withContext DataList(list)
        }
    }

    private fun getJsonString() =
        application.applicationContext.assets.open("nasa_details.json").bufferedReader()
            .use { it.readText() }


}