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

        /*val blockWithoutFieldsActionsAndHoles: Variable =
            Variable(
                Coordinate(0,0), Size(130, 180),
                Pins(mutableListOf(Pin(Size(20,20), Coordinate(0, 0), Side.Up),
                    Pin(Size(20,20), Coordinate(0, 0), Side.Down))));

        val sideMenu = findViewById<LinearLayout>(R.id.linearlayout);
        val temporaryBlock : ConstraintLayout = blockWithoutFieldsActionsAndHoles.makeBlock(this);
        sideMenu.addView(temporaryBlock);*/

        val sideMenu = findViewById<LinearLayout>(R.id.linearlayout);

        val exampleBlock = BlockUI();
        sideMenu.addView(exampleBlock.makeUsualBlock(this));

    }

}