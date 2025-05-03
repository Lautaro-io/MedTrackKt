package com.medtrack.ui.model

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
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