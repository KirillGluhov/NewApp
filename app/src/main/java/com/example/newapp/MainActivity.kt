package com.example.newapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    //var buttonFirst: Button = findViewById<Button>(R.id.button2)
    //var buttonSecond: Button = findViewById<Button>(R.id.button5)
    //var buttonThird: Button = findViewById<Button>(R.id.button6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    //private val text = "Нажата кнопка"
    //private val duration = Toast.LENGTH_SHORT
    //private val toast: Toast = Toast.makeText(applicationContext, text, duration)

    //fun Click(v: View) {
       // when (v.id) {
           // R.id.button2 -> toast.show()
            //R.id.button5 -> toast.show()
            //R.id.button6 -> toast.show()
       // }
    //}

}