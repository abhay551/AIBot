package com.abhay.aibot.di

import com.abhay.aibot.data.api.GeminiApiService
import com.abhay.aibot.data.api.GeminiApiServiceImpl
import com.abhay.aibot.data.local.LocalDBService
import com.abhay.aibot.data.local.LocalDBServiceImpl
import com.abhay.aibot.data.local.SharedAppDatabase
import com.abhay.aibot.data.respository.ChatRepositoryImpl
import com.abhay.aibot.domain.repository.ChatRepository
import com.abhay.aibot.domain.usecase.ClearMessageUseCase
import com.abhay.aibot.domain.usecase.GenerateResponseUseCase
import com.abhay.aibot.domain.usecase.GetMessagesUseCase
import com.abhay.aibot.domain.usecase.SaveMessageUseCase
import com.abhay.aibot.presentation.viewmodel.ChatViewModel
import com.abhay.aibot.presentation.viewmodel.HistoryViewModel
import com.abhay.aibot.presentation.viewmodel.SettingsViewModel
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<HttpClient> { provideHttpClient() }
    single<GeminiApiService> { GeminiApiServiceImpl(get()) }
    single { DatabaseDriverFactory() }
    single { SharedAppDatabase(get(), get()) }
    single<CoroutineScope> { CoroutineScope(SupervisorJob() + Dispatchers.IO) }
    single<LocalDBService> { LocalDBServiceImpl(appDatabase = get()) }
    single<ChatRepository> {
        ChatRepositoryImpl(geminiApiService = get(), localDBService = get())
    }
    single { SaveMessageUseCase(chatRepository = get()) }
    single { GetMessagesUseCase(chatRepository = get()) }
    single { ClearMessageUseCase(chatRepository = get()) }
    single { GenerateResponseUseCase(chatRepository = get()) }
    viewModel { HistoryViewModel(getMessagesUseCase = get()) }
    viewModel { ChatViewModel(generateResponseUseCase = get(), saveMessageUseCase = get()) }
    viewModel { SettingsViewModel(clearMessagesUseCase = get()) }
}