package com.example.newapp
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

class ActivityWithMainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
    }
}