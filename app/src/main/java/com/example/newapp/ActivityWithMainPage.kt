package com.example.newapp

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import uiblockvarioustypesofblocks.*

class ActivityWithMainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page);

        val sideMenu = findViewById<LinearLayout>(R.id.linearlayout);
        val mainField = findViewById<LinearLayout>(R.id.linearlayout2);

        val newButtonVariable = OrdinaryButton("Variable");
        val newButtonInitialization = OrdinaryButton("Initialization");
        val newButtonDirectAssignment = OrdinaryButton("DirectAssignment");
        val newButtonAddition = OrdinaryButton("Addition");
        val newButtonSubtraction = OrdinaryButton("Subtraction");
        val newButtonModulo = OrdinaryButton("Modulo");
        val newButtonDivision = OrdinaryButton("Division");
        val newButtonMultiplication = OrdinaryButton("Multiplication");

        val buttonVariable = newButtonVariable.makeButton(this);
        val buttonInitialization = newButtonInitialization.makeButton(this);
        val buttonDirectAssignment = newButtonDirectAssignment.makeButton(this);
        val buttonAddition = newButtonAddition.makeButton(this);
        val buttonSubtraction = newButtonSubtraction.makeButton(this);
        val buttonModulo = newButtonModulo.makeButton(this);
        val buttonDivision = newButtonDivision.makeButton(this);
        val buttonMultiplication = newButtonMultiplication.makeButton(this);

        sideMenu.addView(buttonVariable);
        sideMenu.addView(buttonInitialization);
        sideMenu.addView(buttonDirectAssignment);
        sideMenu.addView(buttonAddition);
        sideMenu.addView(buttonSubtraction);
        sideMenu.addView(buttonModulo);
        sideMenu.addView(buttonDivision);
        sideMenu.addView(buttonMultiplication);

        val allBlocks = mutableListOf<anyFinalBlock>();

        buttonVariable.setOnClickListener{
            val newVariable = Variable();
            val variableBlock = newVariable.makeBlock(this);
            allBlocks.add(newVariable)
            mainField.addView(variableBlock);
        }

        buttonInitialization.setOnClickListener{
            val newInitialization = Initialization();
            val initializationBlock = newInitialization.makeBlock(this);
            allBlocks.add(newInitialization);
            mainField.addView(initializationBlock);
        }

        buttonDirectAssignment.setOnClickListener{
            val newDirectAssignment = DirectAssignment();
            val directAssignmentBlock = newDirectAssignment.makeBlock(this);
            allBlocks.add(newDirectAssignment);
            mainField.addView(directAssignmentBlock);
        }

        buttonAddition.setOnClickListener{
            val newAddition = Addition();
            val additionBlock = newAddition.makeBlock(this);
            allBlocks.add(newAddition);
            mainField.addView(additionBlock);
        }

        buttonSubtraction.setOnClickListener{
            val newSubtraction = Subtraction();
            val subtractionBlock = newSubtraction.makeBlock(this);
            allBlocks.add(newSubtraction);
            mainField.addView(subtractionBlock );
        }

        buttonModulo.setOnClickListener{
            val newModulo = Modulo();
            val moduloBlock = newModulo.makeBlock(this);
            allBlocks.add(newModulo);
            mainField.addView(moduloBlock);
        }

        buttonDivision.setOnClickListener{
            val newDivision = Division();
            val divisionBlock = newDivision.makeBlock(this);
            allBlocks.add(newDivision);
            mainField.addView(divisionBlock);
        }

        buttonMultiplication.setOnClickListener{
            val newMultiplication = Multiplication();
            val multiplicationBlock = newMultiplication.makeBlock(this);
            allBlocks.add(newMultiplication);
            mainField.addView(multiplicationBlock);
        }

    }

}