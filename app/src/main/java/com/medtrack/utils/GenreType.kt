package com.medtrack.utils

import com.medtrack.R

sealed class GenreType(val image: Int) {

    object Masculino : GenreType(R.drawable.hombre)

    object Femenino : GenreType(R.drawable.mujer)

    companion object {
        fun genreToObj(value: String): GenreType {
            return when (value.lowercase()) {
                "femenino" -> Femenino
                else -> Masculino
            }
        }
    }
}