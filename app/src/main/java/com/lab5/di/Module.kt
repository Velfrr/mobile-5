package com.lab5.di

import android.content.Context
import androidx.room.Room
import com.lab5.data.db.Lab5Database
import com.lab5.ui.screens.subjectDetails.SubjectDetailsViewModel
import com.lab5.ui.screens.subjectsList.SubjectsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Lab5Database> {
        Room.databaseBuilder(
            get<Context>(),
            Lab5Database::class.java, "lab5Database"
        ).build()
    }

    viewModel { SubjectsListViewModel(get()) }
    viewModel { SubjectDetailsViewModel(get()) }
}