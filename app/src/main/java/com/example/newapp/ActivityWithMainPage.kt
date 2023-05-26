package com.example.newapp

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import uiblock.allTemporaryBlocks
import uiblock.numberOfClickedButtons
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

        val allBlocks = Tree();

        buttonVariable.setOnClickListener{
            val newVariable = Variable();
            newVariable.makeBlockAsBlock(this);
            allBlocks.getAllTree().add(TreeNode(allBlocks.getAllTree().size, newVariable, allBlocks.getAllTree().size+1))
            mainField.addView(newVariable.getBlockAsBlock());
        }

        buttonInitialization.setOnClickListener{
            val newInitialization = Initialization();
            newInitialization.makeBlockAsBlock(this);
            allBlocks.getAllTree().add(TreeNode(allBlocks.getAllTree().size, newInitialization, allBlocks.getAllTree().size+1))
            mainField.addView(newInitialization.getBlockAsBlock());
        }

        buttonDirectAssignment.setOnClickListener{
            val newDirectAssignment = DirectAssignment();
            newDirectAssignment.makeBlockAsBlock(this);
            allBlocks.getAllTree().add(TreeNode(allBlocks.getAllTree().size, newDirectAssignment, allBlocks.getAllTree().size+1))
            mainField.addView(newDirectAssignment.getBlockAsBlock());
        }

        buttonAddition.setOnClickListener{
            val newAddition = Addition();
            newAddition.makeBlockAsBlock(this);
            allBlocks.getAllTree().add(TreeNode(allBlocks.getAllTree().size, newAddition, allBlocks.getAllTree().size+1))
            mainField.addView(newAddition.getBlockAsBlock());
        }

        buttonSubtraction.setOnClickListener{
            val newSubtraction = Subtraction();
            newSubtraction.makeBlockAsBlock(this);
            allBlocks.getAllTree().add(TreeNode(allBlocks.getAllTree().size, newSubtraction, allBlocks.getAllTree().size+1))
            mainField.addView(newSubtraction.getBlockAsBlock());
        }

        buttonModulo.setOnClickListener{
            val newModulo = Modulo();
            newModulo.makeBlockAsBlock(this);
            allBlocks.getAllTree().add(TreeNode(allBlocks.getAllTree().size, newModulo, allBlocks.getAllTree().size+1))
            mainField.addView(newModulo.getBlockAsBlock());
        }

        buttonDivision.setOnClickListener{
            val newDivision = Division();
            newDivision.makeBlockAsBlock(this);
            allBlocks.getAllTree().add(TreeNode(allBlocks.getAllTree().size, newDivision, allBlocks.getAllTree().size+1))
            mainField.addView(newDivision.getBlockAsBlock());
        }

        buttonMultiplication.setOnClickListener{
            val newMultiplication = Multiplication();
            newMultiplication.makeBlockAsBlock(this);
            allBlocks.getAllTree().add(TreeNode(allBlocks.getAllTree().size, newMultiplication, allBlocks.getAllTree().size+1))
            mainField.addView(newMultiplication.getBlockAsBlock());
        }

        if (numberOfClickedButtons == 2)
        {
            var blockParent: anyFinalBlock? = null;
            var blockChild: anyFinalBlock? = null;

            var treeNodeTemporaryOne: TreeNode? = null;
            var treeNodeTemporaryTwo: TreeNode? = null;

            var listOfAll = allBlocks.getAllTree();
            for (treeNodeTemporary in listOfAll)
            {
                var block = treeNodeTemporary.value;

                if (block is Variable)
                {
                    if (block.getIsButtonPressedDown() && !block.getIsButtonPressedUp())
                    {
                        blockParent = block;
                        treeNodeTemporaryOne = treeNodeTemporary;
                    }
                    else if (!block.getIsButtonPressedDown() && block.getIsButtonPressedUp())
                    {
                        blockChild = block;
                        treeNodeTemporaryTwo = treeNodeTemporary;
                    }
                }
                else if (block is Initialization)
                {
                    if (block.getIsButtonPressedDown() && !block.getIsButtonPressedUp())
                    {
                        blockParent = block;
                        treeNodeTemporaryOne = treeNodeTemporary;
                    }
                    else if (!block.getIsButtonPressedDown() && block.getIsButtonPressedUp())
                    {
                        blockChild = block;
                        treeNodeTemporaryTwo = treeNodeTemporary;
                    }
                }
                else if (block is DirectAssignment)
                {
                    if (block.getIsButtonPressedDown() && !block.getIsButtonPressedUp())
                    {
                        blockParent = block;
                        treeNodeTemporaryOne = treeNodeTemporary;
                    }
                    else if (!block.getIsButtonPressedDown() && block.getIsButtonPressedUp())
                    {
                        blockChild = block;
                        treeNodeTemporaryTwo = treeNodeTemporary;
                    }

                }
                else if (block is Addition)
                {
                    if (block.getIsButtonPressedDown() && !block.getIsButtonPressedUp())
                    {
                        blockParent = block;
                        treeNodeTemporaryOne = treeNodeTemporary;
                    }
                    else if (!block.getIsButtonPressedDown() && block.getIsButtonPressedUp())
                    {
                        blockChild = block;
                        treeNodeTemporaryTwo = treeNodeTemporary;
                    }

                }
                else if (block is Subtraction)
                {
                    if (block.getIsButtonPressedDown() && !block.getIsButtonPressedUp())
                    {
                        blockParent = block;
                        treeNodeTemporaryOne = treeNodeTemporary;
                    }
                    else if (!block.getIsButtonPressedDown() && block.getIsButtonPressedUp())
                    {
                        blockChild = block;
                        treeNodeTemporaryTwo = treeNodeTemporary;
                    }

                }
                else if (block is Modulo)
                {
                    if (block.getIsButtonPressedDown() && !block.getIsButtonPressedUp())
                    {
                        blockParent = block;
                        treeNodeTemporaryOne = treeNodeTemporary;
                    }
                    else if (!block.getIsButtonPressedDown() && block.getIsButtonPressedUp())
                    {
                        blockChild = block;
                        treeNodeTemporaryTwo = treeNodeTemporary;
                    }

                }
                else if (block is Division)
                {
                    if (block.getIsButtonPressedDown() && !block.getIsButtonPressedUp())
                    {
                        blockParent = block;
                        treeNodeTemporaryOne = treeNodeTemporary;
                    }
                    else if (!block.getIsButtonPressedDown() && block.getIsButtonPressedUp())
                    {
                        blockChild = block;
                        treeNodeTemporaryTwo = treeNodeTemporary;
                    }

                }
                else if (block is Multiplication)
                {
                    if (block.getIsButtonPressedDown() && !block.getIsButtonPressedUp())
                    {
                        blockParent = block;
                        treeNodeTemporaryOne = treeNodeTemporary;
                    }
                    else if (!block.getIsButtonPressedDown() && block.getIsButtonPressedUp())
                    {
                        blockChild = block;
                        treeNodeTemporaryTwo = treeNodeTemporary;
                    }

                }
            }

            val idChild = treeNodeTemporaryTwo?.id;
            val idParent = treeNodeTemporaryOne?.id;

            if (idChild != null && idParent != null)
            {
                var child = mainField.getChildAt(idChild);
                var parent = mainField.getChildAt(idParent);

                mainField.removeView(child);
                mainField.removeView(parent);

                mainField.addView(parent, idChild);
                mainField.addView(child, idParent);
            }


            var temporaryNodeZero: anyFinalBlock? = treeNodeTemporaryOne?.value;

            if (treeNodeTemporaryOne != null && treeNodeTemporaryTwo != null)
            {
                if (temporaryNodeZero != null) {
                    temporaryNodeZero = treeNodeTemporaryOne.value;
                    treeNodeTemporaryOne.value = treeNodeTemporaryTwo.value;
                    treeNodeTemporaryTwo.value = temporaryNodeZero;
                };

            };



        }
    }

}