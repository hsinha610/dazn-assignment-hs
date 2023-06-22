package com.hsinha610.daznassignmenths.data.repo

import com.hsinha610.daznassignmenths.data.models.DataList

interface Repository {
   suspend fun getData() : DataList
}