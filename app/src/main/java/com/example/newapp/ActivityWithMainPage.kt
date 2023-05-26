package com.example.newapp

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import uiblockvarioustypesofblocks.*

class ActivityWithMainPage : AppCompatActivity() {
    /* drag'n'drop с вставкой одного блока в другой. Всего их два здесь
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val block1 = findViewById<LinearLayout>(R.id.block1)
        val block2 = findViewById<LinearLayout>(R.id.block2)

        // Устанавливаем слушатели Drag and Drop для блоков
        block1.setOnLongClickListener { startDrag(it) }
        block2.setOnLongClickListener { startDrag(it) }

        // Устанавливаем слушатель Drag and Drop на контейнеры блоков
        val block1Content = findViewById<LinearLayout>(R.id.block1_content)
        val block2Content = findViewById<LinearLayout>(R.id.block2_content)
        block1Content.setOnDragListener { _, event -> onDrag(event) }
        block2Content.setOnDragListener { _, event -> onDrag(event) }
    }

    private fun startDrag(view: View): Boolean {
        val item = ClipData.Item(view.tag as? CharSequence)
        val dragData = ClipData(
            view.tag.toString(),
            arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
            item
        )
        view.startDragAndDrop(dragData, View.DragShadowBuilder(view), null, 0)
        return true
    }

    private fun onDrag(event: DragEvent): Boolean {
        val container = event.localState as? ViewGroup
        val draggedView = event.localState as? View

        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    container?.setBackgroundColor(Color.GREEN)
                    container?.invalidate()
                    return true
                }
                return false
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                container?.setBackgroundColor(Color.BLUE)
                container?.invalidate()
                return true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                container?.setBackgroundColor(Color.GREEN)
                container?.invalidate()
                return true
            }

            DragEvent.ACTION_DROP -> {
                val text = event.clipData.getItemAt(0).text
                container?.setBackgroundColor(Color.GREEN)
                container?.invalidate()

                // Создаем новый блок, который будет добавлен внутрь текущего контейнера
                val newBlock = createBlock(text.toString())

                // Добавляем новый блок в контейнер
                container?.addView(newBlock)
                return true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                container?.setBackgroundColor(Color.WHITE)
                container?.invalidate()
                return true
            }

            else -> return false
        }
    }

    private fun createBlock(text: String): View {
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val block = LinearLayout(this)
        block.layoutParams = layoutParams
        block.background = resources.getDrawable(R.drawable.block_background)
        block.orientation = LinearLayout.VERTICAL
        block.setPadding(16, 16, 16, 16)

        val textView = TextView(this)
        textView.layoutParams = layoutParams
        textView.text = text
        textView.setTextColor(Color.WHITE)
        textView.textSize = 18f

        block.addView(textView)
        return block
    }
}
*/
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