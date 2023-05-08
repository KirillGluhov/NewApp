package com.example.newapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonMain = findViewById<Button>(R.id.button6);
        var buttonSettings = findViewById<Button>(R.id.button5);
        var buttonExit = findViewById<Button>(R.id.button2);

        buttonMain.setOnClickListener{
            val intent = Intent(this, ActivityWithMainPage::class.java);
            startActivity(intent);
        }

        buttonSettings.setOnClickListener{
            val intent = Intent(this, ActivityWithSettings::class.java);
            startActivity(intent);
        }

        buttonExit.setOnClickListener{
            finish();
        }
    }
}