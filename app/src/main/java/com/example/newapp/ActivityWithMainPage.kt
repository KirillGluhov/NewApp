package com.example.newapp

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import uiblock.allTemporaryBlocks
import uiblock.numberOfClickedButtons
import uiblockvarioustypesofblocks.*
import java.util.Hashtable

class ActivityWithMainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page);

        val sideMenu = findViewById<LinearLayout>(R.id.linearlayout);
        val mainField = findViewById<LinearLayout>(R.id.linearlayout2);

        val newButtonInitialization = OrdinaryButton("Initialization");
        val newButtonDirectAssignment = OrdinaryButton("DirectAssignment");
        val newButtonPrint = OrdinaryButton("Print");
        val newButtonStart = OrdinaryButton("Start");

        val buttonInitialization = newButtonInitialization.makeButton(this);
        val buttonDirectAssignment = newButtonDirectAssignment.makeButton(this);
        val buttonPrint = newButtonPrint.makeButton(this);
        val buttonStart = newButtonStart.makeButton(this);

        sideMenu.addView(buttonInitialization);
        sideMenu.addView(buttonDirectAssignment);
        sideMenu.addView(buttonPrint);
        sideMenu.addView(buttonStart);

        val allBlocks = mutableListOf<anyFinalBlock>();

        buttonInitialization.setOnClickListener{
            val newInitialization = Initialization();
            newInitialization.makeBlockAsBlock(this);
            allBlocks.add(newInitialization)
            mainField.addView(newInitialization.getBlockAsBlock());
        }

        buttonDirectAssignment.setOnClickListener{
            val newDirectAssignment = DirectAssignment();
            newDirectAssignment.makeBlockAsBlock(this);
            allBlocks.add(newDirectAssignment)
            mainField.addView(newDirectAssignment.getBlockAsBlock());
        }

        buttonPrint.setOnClickListener{
            val newPrint = Print();
            newPrint.makeBlockAsBlock(this);
            allBlocks.add(newPrint);
            mainField.addView(newPrint.getBlockAsBlock());
        }

        val allVariables = Hashtable<String?, Int?>();

        buttonStart.setOnClickListener{
            for (block in allBlocks)
            {
                if (block is Initialization)
                {
                    var temporaryEditText = block.getEditedTextFirst();
                    var stringOnThisBlock : String = temporaryEditText?.text.toString();
                    var potentialVariables = stringOnThisBlock.split(" ");

                    for (variable in potentialVariables)
                    {
                        if (variable[0].isLetter())
                        {
                            if (variable[variable.length-1] == ',')
                            {
                                allVariables[variable.substring(0, variable.length-2)] = 0;
                            }
                            else
                            {
                                allVariables[variable] = 0;
                            }
                        }
                    }
                }
                else if (block is DirectAssignment)
                {
                    var stringOnThisBlock = block.getEditedTextFirst()?.text.toString();
                    var potentialVariables = stringOnThisBlock.split(" ");

                    var newInterpretator = Interpretate;

                    if (potentialVariables.size > 1)
                    {
                        val message = "You can't use directAssignment for several variables";
                        val newToast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
                        newToast.show()
                    }
                    else if (potentialVariables.size == 1)
                    {
                        var stringOperations = block.getEditedTextSecond()?.text.toString();

                        for (variable in potentialVariables)
                        {
                            for (temporaryValue in allVariables)
                            {
                                if (variable == temporaryValue.key.toString())
                                {
                                    allVariables[variable] = newInterpretator.calculate(stringOperations, allVariables);
                                }
                            }
                        }

                    }
                }
                else if (block is Print)
                {

                    var stringOnThisBlock = block.getEditedTextFirst()?.text.toString();
                    var potentialVariables = stringOnThisBlock.split(" ");

                    for (variable in potentialVariables)
                    {
                        for (value in allVariables)
                        {
                            var finalString: String = variable;
                            if (variable[variable.length-1] == ',')
                            {
                                finalString = variable.substring(0, variable.length-2);
                            }

                            if (finalString == value.key)
                            {
                                val message = value.key + " " + value.value;
                                val newToast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
                                newToast.show()
                            }
                        }
                    }

                }
            }
        }


    }

}