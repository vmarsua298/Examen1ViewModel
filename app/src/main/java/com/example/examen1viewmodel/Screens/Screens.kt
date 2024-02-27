package com.example.examen1viewmodel.Screens

import android.annotation.SuppressLint
import androidx.annotation.StringRes

sealed class Screens(@SuppressLint("SupportAnnotationUsage") @StringRes val ruta: String){
    object ScreenMain : Screens("ScreenMain")
    object ScreenVoid : Screens("ScreenVoid")
}
