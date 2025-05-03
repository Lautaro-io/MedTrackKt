package com.medtrack.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.medtrack.data.dao.PacienteDao
import com.medtrack.data.dao.RegistroPacienteDao
import com.medtrack.data.entities.PacienteEntity
import com.medtrack.data.entities.RegistroPacienteEntity

@Database(
    entities = [PacienteEntity::class,
        RegistroPacienteEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase(){

    abstract fun pacienteDao(): PacienteDao

    abstract fun registroPacienteDao(): RegistroPacienteDao
}