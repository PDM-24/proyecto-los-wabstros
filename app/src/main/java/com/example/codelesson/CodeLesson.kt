package com.example.codelesson

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Creando la activity secundaria para utilizar dagger-hilt
@HiltAndroidApp
class CodeLesson : Application()