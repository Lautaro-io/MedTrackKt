package com.medtrack.di

import android.content.Context
import androidx.room.Room
import com.medtrack.data.dao.PacienteDao
import com.medtrack.data.dao.RegistroPacienteDao
import com.medtrack.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object Module {

    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providePatientDao(database: AppDatabase): PacienteDao {
        return database.pacienteDao()
    }

    @Provides
    fun provideRegisterDao(database: AppDatabase) : RegistroPacienteDao{
        return database.registroPacienteDao()
    }
}