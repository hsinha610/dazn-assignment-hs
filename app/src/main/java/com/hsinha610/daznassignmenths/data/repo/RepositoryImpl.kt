package com.hsinha610.daznassignmenths.data.repo

import android.app.Application
import com.google.gson.Gson
import com.hsinha610.daznassignmenths.data.models.DataList
import com.hsinha610.daznassignmenths.data.models.DataListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val application: Application,
    private val gson: Gson
) : Repository {


    override suspend fun getData(): DataList {
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