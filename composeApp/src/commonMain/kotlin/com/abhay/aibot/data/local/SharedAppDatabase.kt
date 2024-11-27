package com.abhay.aibot.data.local

import com.abhay.aibot.AppDataBase
import com.abhay.aibot.AppDatabaseQueries
import com.abhay.aibot.di.DatabaseDriverFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class SharedAppDatabase(
    private val coroutineScope: CoroutineScope,
    private val driverFactory: DatabaseDriverFactory
) {
    private val database: Deferred<AppDataBase> by lazy {
        coroutineScope.async {
            AppDataBase(driverFactory.createDriver())
        }
    }

    suspend operator fun <R> invoke(block: suspend (AppDataBase) -> R): R {
        return block(database.await())
    }

    suspend fun getAppDBQueries(): AppDatabaseQueries {
        return database.await().appDatabaseQueries
    }
}