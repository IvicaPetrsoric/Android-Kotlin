package com.example.androidkotlin

import android.app.Application

class EmployeeApp:Application() {

    val db by lazy {
        EmployeeDatabase.getInstance(this)
    }
}