package com.example.newapp

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import uiblockvarioustypesofblocks.*
import uiblock.*

class ActivityWithMainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val sideMenu = findViewById<LinearLayout>(R.id.linearlayout);

        val exampleBlock = BlockUI();
        //val exampleButton = Pin(Size(20,20), Coordinate(0, 0), Side.Up);
        //sideMenu.addView(exampleButton.makePin(this));
        sideMenu.addView(exampleBlock.makeDefaultBlock(this));

    }

}