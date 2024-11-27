@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.abhay.aibot.di

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.abhay.aibot.AppDataBase
import org.koin.mp.KoinPlatform

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            AppDataBase.Schema.synchronous(),
            KoinPlatform.getKoin().get(),
            "chat_app.db"
        )
    }
}