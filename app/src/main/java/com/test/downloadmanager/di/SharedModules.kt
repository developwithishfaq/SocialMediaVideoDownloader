package com.test.downloadmanager.di

import android.content.Context
import androidx.room.Room
import com.example.socialmediadownloader.EasyDownloader
import com.test.downloadmanager.data.local.AppDatabase
import com.test.downloadmanager.data.local.InProgressDao
import com.test.downloadmanager.data.repository.MediaDownloaderImpl
import com.test.downloadmanager.data.repository.ScrapeLinkRepositoryImpl
import com.test.downloadmanager.data.repository.StorageRepositoryImpl
import com.test.downloadmanager.domain.repository.MediaDownloader
import com.test.downloadmanager.domain.repository.ScrapeLinkRepository
import com.test.downloadmanager.domain.repository.StorageRepository
import com.test.downloadmanager.domain.usecases.DownloadVideoUseCase
import com.test.downloadmanager.domain.usecases.GetAllDownloads
import com.test.downloadmanager.domain.usecases.GetAllInProgressVideosUseCase
import com.test.downloadmanager.domain.usecases.ParseLinkUseCase
import com.test.downloadmanager.presentation.downloads.DownloadsViewModel
import com.test.downloadmanager.presentation.home.HomeScreenViewModel
import com.test.downloadmanager.presentation.inProgress.InProgressViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val SharedModules = module {
    single {
        EasyDownloader()
    }
    single<ScrapeLinkRepository> {
        ScrapeLinkRepositoryImpl(get())
    }
    single {
        ParseLinkUseCase(get())
    }
    single {
        createDatabase(get())
    }
    single<MediaDownloader> {
        MediaDownloaderImpl(get())
    }
    single<InProgressDao> {
        get<AppDatabase>().inProgressDao()
    }
    single {
        DownloadVideoUseCase(get(), get())
    }
    viewModel {
        HomeScreenViewModel(get(), get())
    }
    single {
        GetAllInProgressVideosUseCase(get(), get())
    }
    viewModel {
        InProgressViewModel(get())
    }
    single<StorageRepository> {
        StorageRepositoryImpl()
    }
    single {
        GetAllDownloads(get(), get())
    }
    viewModel {
        DownloadsViewModel(get())
    }
}

fun createDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "appDb")
        .fallbackToDestructiveMigration()
        .build()
}