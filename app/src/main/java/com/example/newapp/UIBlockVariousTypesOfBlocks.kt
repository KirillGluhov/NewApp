package uiblockvarioustypesofblocks

import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.newapp.R
import uiblock.*

abstract class Operator(coordinateOfBlock: Coordinate,
                        sizeOfBlock: Size,
                        pinsOfBlock: Int,
                        isClicked: Boolean,
                        symbolOfOperation: String,
                        blockAsBlock: ConstraintLayout?,
                        isButtonPressedUp: Boolean,
                        isButtonPressedDown: Boolean) :
    BlockUI(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        isClicked,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown)
{
    private var symbolOfOperation: String = symbolOfOperation

    fun getSymbolOfOperation(): String {
        return symbolOfOperation
    }

    fun setSymbolOfOperation(newSymbolOfOperation: String) {
        symbolOfOperation = newSymbolOfOperation
    }
}

abstract class BinaryOperator(coordinateOfBlock: Coordinate = Coordinate(0,0),
                              sizeOfBlock: Size = Size(130, 180),
                              pinsOfBlock: Int = 2,
                              symbolOfOperation: String = "!",
                              isClicked: Boolean = false,
                              blockAsBlock: ConstraintLayout?,
                              isButtonPressedUp: Boolean = false,
                              isButtonPressedDown: Boolean = false ) :
    Operator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        isClicked,
        symbolOfOperation,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown)
{
    override fun makeBlockAsBlock(context: Context) //вернуть abstract class
    {
        val parentLayout = ConstraintLayout(context).apply {
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                (getSizeOfBlock().getWidth() * context.resources.displayMetrics.density).toInt(),
                (getSizeOfBlock().getHeight() * context.resources.displayMetrics.density).toInt()
            ).apply {
                topMargin = (-20 * context.resources.displayMetrics.density).toInt();
            }
            setBackgroundResource(R.drawable.empty_element)
        }

        val childLayout = ConstraintLayout(context).apply {
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                (getSizeOfBlock().getWidth() * context.resources.displayMetrics.density).toInt(),
                ((getSizeOfBlock().getHeight() - 20) * context.resources.displayMetrics.density).toInt()
            ).apply {
                bottomToBottom = ConstraintSet.PARENT_ID
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
            }
            setBackgroundResource(R.drawable.block_border)
        }

        ///////////////////////////////////////////////

        val microFirst = BlockUI(Coordinate(0,0),
            Size((getSizeOfBlock().getHeight()*0.4).toInt(),
                (getSizeOfBlock().getWidth()*0.4).toInt() ),
            2, false);
        microFirst.makeBlockAsBlock(context);
        val microFirstBlock = microFirst.getBlockAsBlock();

        val microSecond = BlockUI(Coordinate(0,0),
            Size((getSizeOfBlock().getHeight()*0.4).toInt(),
                (getSizeOfBlock().getWidth()*0.4).toInt() ),
            2, false);
        microSecond.makeBlockAsBlock(context);
        val microSecondBlock = microSecond.getBlockAsBlock();

        val textView = TextView(context).apply{
            id = View.generateViewId();
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomToBottom = ConstraintSet.PARENT_ID
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
                topToTop = ConstraintSet.PARENT_ID
            }
            text = getSymbolOfOperation();
            setTextAppearance(R.style.Text);
        }

        ///////////////////////////////////////////////

        if (getPinsOfBlock() == 2)
        {
            var isButtonPressedUp = false;
            val buttonUp = Button(context).apply {
                id = View.generateViewId()
                layoutParams = ConstraintLayout.LayoutParams(
                    (20 * context.resources.displayMetrics.density).toInt(),
                    (20 * context.resources.displayMetrics.density).toInt()
                ).apply {
                    topMargin = (-20 * context.resources.displayMetrics.density).toInt()
                    startToStart = ConstraintSet.PARENT_ID
                    endToEnd = ConstraintSet.PARENT_ID
                }
                setOnClickListener{
                    if (isButtonPressedUp) {
                        setBackgroundResource(R.drawable.pin)
                        numberOfClickedButtons -= 1;
                        isButtonPressedUp = false;
                        setIsClicked(false);
                        if (allTemporaryBlocks.size > 0)
                        {
                            for (i in allTemporaryBlocks.indices)
                            {
                                if (allTemporaryBlocks[i] == getBlockAsBlock())
                                {
                                    allTemporaryBlocks.removeAt(i);
                                }
                            }
                        }
                    } else if (numberOfClickedButtons < 2) {
                        setBackgroundResource(R.drawable.square);
                        numberOfClickedButtons += 1;
                        isButtonPressedUp = true;
                        setIsClicked(true);
                        if (getBlockAsBlock() != null)
                        {
                            allTemporaryBlocks.add(getBlockAsBlock()!!);
                        }
                    }
                }
                setBackgroundResource(R.drawable.pin)
            }

            var isButtonPressedDown = false;
            val buttonDown = Button(context).apply {
                id = View.generateViewId()
                layoutParams = ConstraintLayout.LayoutParams(
                    (20 * context.resources.displayMetrics.density).toInt(),
                    (20 * context.resources.displayMetrics.density).toInt()
                ).apply {
                    topToTop = ConstraintSet.PARENT_ID
                    bottomToBottom = ConstraintSet.PARENT_ID
                    startToStart = ConstraintSet.PARENT_ID
                    endToEnd = ConstraintSet.PARENT_ID
                    verticalBias = 1.0f
                }
                setOnClickListener{
                    if (isButtonPressedDown) {
                        setBackgroundResource(R.drawable.pin)
                        numberOfClickedButtons -= 1;
                        isButtonPressedDown = false;
                        setIsClicked(false);
                        if (allTemporaryBlocks.size > 0)
                        {
                            for (i in allTemporaryBlocks.indices)
                            {
                                if (allTemporaryBlocks[i] == getBlockAsBlock())
                                {
                                    allTemporaryBlocks.removeAt(i);
                                }
                            }
                        }
                    } else if (numberOfClickedButtons < 2) {
                        setBackgroundResource(R.drawable.square);
                        numberOfClickedButtons += 1;
                        isButtonPressedDown = true;
                        setIsClicked(true);
                        if (getBlockAsBlock() != null)
                        {
                            allTemporaryBlocks.add(getBlockAsBlock()!!);
                        }
                    }
                }
                setBackgroundResource(R.drawable.pin)
            }

            parentLayout.addView(buttonUp)
            parentLayout.addView(childLayout)

            childLayout.addView(microFirstBlock);
            childLayout.addView(textView)
            childLayout.addView(microSecondBlock);
            childLayout.addView(buttonDown)


            val constraintSet = ConstraintSet().apply {
                clone(parentLayout)
                connect(buttonUp.id, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                connect(buttonUp.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
            }

            val newConstraintSet = ConstraintSet().apply {
                clone(childLayout)
                if (microSecondBlock != null) {
                    connect(microSecondBlock.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP)
                    connect(microSecondBlock.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                    connect(microSecondBlock.id, ConstraintSet.LEFT, childLayout.id, ConstraintSet.LEFT);
                };

                connect(textView.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM)
                connect(textView.id, ConstraintSet.LEFT, childLayout.id, ConstraintSet.LEFT)
                connect(textView.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT)
                connect(textView.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP)

                if (microFirstBlock != null)
                {
                    connect(microFirstBlock.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                    connect(microFirstBlock.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                    connect(microFirstBlock.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT);
                }

                connect(buttonDown.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP)
                connect(buttonDown.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM)
                connect(buttonDown.id, ConstraintSet.START, childLayout.id, ConstraintSet.START)
                connect(buttonDown.id, ConstraintSet.END, childLayout.id, ConstraintSet.END)
            }

            newConstraintSet.applyTo(childLayout)
            constraintSet.applyTo(parentLayout)
        }
        else if (getPinsOfBlock() == 0)
        {
            parentLayout.addView(childLayout)

            childLayout.setBackgroundResource(R.drawable.button_border);
            childLayout.addView(microFirstBlock);
            childLayout.addView(textView)
            childLayout.addView(microSecondBlock);

            val constraintSet = ConstraintSet().apply {
                clone(childLayout)

                if (microSecondBlock != null)
                {
                    connect(microSecondBlock.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                    connect(microSecondBlock.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                    connect(microSecondBlock.id, ConstraintSet.LEFT, childLayout.id, ConstraintSet.LEFT);
                }

                connect(textView.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM)
                connect(textView.id, ConstraintSet.LEFT, childLayout.id, ConstraintSet.LEFT)
                connect(textView.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT)
                connect(textView.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP)

                if (microFirstBlock != null)
                {
                    connect(microFirstBlock.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                    connect(microFirstBlock.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                    connect(microFirstBlock.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT);
                }

            }

            constraintSet.applyTo(childLayout);
        }

        setBlockAsBlock(parentLayout);
    }

}

abstract class UnaryOperator(coordinateOfBlock: Coordinate,
                             sizeOfBlock: Size,
                             pinsOfBlock: Int,
                             symbolOfOperation: String,
                             isClicked: Boolean,
                             blockAsBlock: ConstraintLayout?,
                             isButtonPressedUp: Boolean,
                             isButtonPressedDown: Boolean) :
    Operator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        isClicked,
        symbolOfOperation,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown)
{

}

abstract class TernaryOperator(coordinateOfBlock: Coordinate,
                               sizeOfBlock: Size,
                               pinsOfBlock: Int,
                               symbolOfOperation: String,
                               isClicked: Boolean,
                               blockAsBlock: ConstraintLayout?,
                               isButtonPressedUp: Boolean,
                               isButtonPressedDown: Boolean) :
    Operator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        isClicked,
        symbolOfOperation,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown)
{

}

abstract class AssignmentOperator(coordinateOfBlock: Coordinate,
                                  sizeOfBlock: Size,
                                  pinsOfBlock: Int,
                                  symbolOfOperation: String,
                                  isClicked: Boolean,
                                  blockAsBlock: ConstraintLayout?,
                                  isButtonPressedUp: Boolean,
                                  isButtonPressedDown: Boolean) :
    BinaryOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation,
        isClicked,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown)
{

}

abstract class ArithmeticOperator(coordinateOfBlock: Coordinate = Coordinate(0,0),
                              sizeOfBlock: Size = Size(130, 180),
                              pinsOfBlock: Int = 2,
                              symbolOfOperation: String = "!",
                                  isClicked: Boolean = false,
                                  blockAsBlock: ConstraintLayout? = null,
                                  isButtonPressedUp: Boolean = false,
                                  isButtonPressedDown: Boolean = false) :
    BinaryOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation,
        isClicked,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown)
{


}

interface anyFinalBlock{

}

enum class FinalClasses
{
    Variable,
    Initialization,
    DirectAssignment,
    Addition,
    Subtraction,
    Modulo,
    Division,
    Multiplication

}

class Addition(coordinateOfBlock: Coordinate = Coordinate(0,0),
               sizeOfBlock: Size = Size(130, 180),
               pinsOfBlock: Int = 2,
               symbolOfOperation: String = "+",
               isClicked: Boolean = false,
               blockAsBlock: ConstraintLayout? = null,
               isButtonPressedUp: Boolean = false,
               isButtonPressedDown: Boolean = false) :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation,
        isClicked,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown), anyFinalBlock
{

}

class Subtraction(coordinateOfBlock: Coordinate = Coordinate(0,0),
                   sizeOfBlock: Size = Size(130, 180),
                   pinsOfBlock: Int = 2,
                   symbolOfOperation: String = "-",
                  isClicked: Boolean = false,
                  blockAsBlock: ConstraintLayout? = null,
                  isButtonPressedUp: Boolean = false,
                  isButtonPressedDown: Boolean = false) :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation,
        isClicked,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown), anyFinalBlock
{

}

class Modulo(coordinateOfBlock: Coordinate = Coordinate(0,0),
             sizeOfBlock: Size = Size(130, 180),
             pinsOfBlock: Int = 2,
             symbolOfOperation: String = "%",
             isClicked: Boolean = false,
             blockAsBlock: ConstraintLayout? = null,
             isButtonPressedUp: Boolean = false,
             isButtonPressedDown: Boolean = false) :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation,
        isClicked,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown), anyFinalBlock
{

}

class Division(coordinateOfBlock: Coordinate = Coordinate(0,0),
               sizeOfBlock: Size = Size(130, 180),
               pinsOfBlock: Int = 2,
               symbolOfOperation: String = "/",
               isClicked: Boolean = false,
               blockAsBlock: ConstraintLayout? = null,
               isButtonPressedUp: Boolean = false,
               isButtonPressedDown: Boolean = false) :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation,
        isClicked,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown), anyFinalBlock
{

}

class Multiplication(coordinateOfBlock: Coordinate = Coordinate(0,0),
                     sizeOfBlock: Size = Size(130, 180),
                     pinsOfBlock: Int = 2,
                     symbolOfOperation: String = "*",
                     isClicked: Boolean = false,
                     blockAsBlock: ConstraintLayout? = null,
                     isButtonPressedUp: Boolean = false,
                     isButtonPressedDown: Boolean = false) :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation,
        isClicked,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown), anyFinalBlock
{

}

class Initialization(coordinateOfBlock: Coordinate = Coordinate(0,0),
                     sizeOfBlock: Size = Size(130, 180),
                     pinsOfBlock: Int = 2,
                     symbolOfOperation: String = "",
                     isClicked: Boolean = false,
                     blockAsBlock: ConstraintLayout? = null,
                     isButtonPressedUp: Boolean = false,
                     isButtonPressedDown: Boolean = false) :
    UnaryOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation,
        isClicked,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown), anyFinalBlock
{
    override fun makeBlockAsBlock(context: Context)
    {
        val parentLayout = ConstraintLayout(context).apply {
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                (getSizeOfBlock().getWidth() * context.resources.displayMetrics.density).toInt(),
                (getSizeOfBlock().getHeight() * context.resources.displayMetrics.density).toInt()
            ).apply {
                topMargin = (-20 * context.resources.displayMetrics.density).toInt();
            }
            setBackgroundResource(R.drawable.empty_element)
        }

        val childLayout = ConstraintLayout(context).apply {
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                (getSizeOfBlock().getWidth() * context.resources.displayMetrics.density).toInt(),
                ((getSizeOfBlock().getHeight() - 20) * context.resources.displayMetrics.density).toInt()
            ).apply {
                bottomToBottom = ConstraintSet.PARENT_ID
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
            }
            setBackgroundResource(R.drawable.block_border)
        }

        ///////////////////////////////////////////////

        val microFirst = BlockUI(Coordinate(0,0),
            Size((getSizeOfBlock().getHeight()*0.4).toInt(),
                (getSizeOfBlock().getWidth()*0.4).toInt() ),
            2, false);
        microFirst.makeBlockAsBlock(context);
        val microFirstBlock = microFirst.getBlockAsBlock();

        val editText = EditText(context).apply {
            id = EditText.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                ((getSizeOfBlock().getWidth()*0.4).toInt() * context.resources.displayMetrics.density).toInt(),
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                leftMargin = (10 * context.resources.displayMetrics.density).toInt();
            }
            hint = "Var"
            inputType = InputType.TYPE_CLASS_TEXT
            setTextAppearance(R.style.text);
            setBackgroundResource(R.drawable.substrate);
        }

        ///////////////////////////////////////////////

        if (getPinsOfBlock() == 2)
        {
            val buttonUp = Button(context).apply {
                id = View.generateViewId()
                layoutParams = ConstraintLayout.LayoutParams(
                    (20 * context.resources.displayMetrics.density).toInt(),
                    (20 * context.resources.displayMetrics.density).toInt()
                ).apply {
                    topMargin = (-20 * context.resources.displayMetrics.density).toInt()
                    startToStart = ConstraintSet.PARENT_ID
                    endToEnd = ConstraintSet.PARENT_ID
                }
                setOnClickListener{

                    if (getIsButtonPressedUp()) {
                        setBackgroundResource(R.drawable.pin)
                        numberOfClickedButtons -= 1;
                        setIsButtonPressedUp(false);
                        setIsClicked(false);
                        if (allTemporaryBlocks.size > 0)
                        {
                            for (i in allTemporaryBlocks.indices)
                            {
                                if (allTemporaryBlocks[i] == getBlockAsBlock())
                                {
                                    allTemporaryBlocks.removeAt(i);
                                }
                            }
                        }
                    } else if (numberOfClickedButtons < 2) {
                        setBackgroundResource(R.drawable.square);
                        numberOfClickedButtons += 1;
                        setIsButtonPressedUp(true);
                        setIsClicked(true);
                        if (getBlockAsBlock() != null)
                        {
                            allTemporaryBlocks.add(getBlockAsBlock()!!);
                        }
                    }
                }
                setBackgroundResource(R.drawable.pin)
            }

            val buttonDown = Button(context).apply {
                id = View.generateViewId()
                layoutParams = ConstraintLayout.LayoutParams(
                    (20 * context.resources.displayMetrics.density).toInt(),
                    (20 * context.resources.displayMetrics.density).toInt()
                ).apply {
                    topToTop = ConstraintSet.PARENT_ID
                    bottomToBottom = ConstraintSet.PARENT_ID
                    startToStart = ConstraintSet.PARENT_ID
                    endToEnd = ConstraintSet.PARENT_ID
                    verticalBias = 1.0f
                }
                setOnClickListener{
                    if (getIsButtonPressedDown()) {
                        setBackgroundResource(R.drawable.pin)
                        numberOfClickedButtons -= 1;
                        setIsButtonPressedDown(false);
                        setIsClicked(false);
                        if (allTemporaryBlocks.size > 0)
                        {
                            for (i in allTemporaryBlocks.indices)
                            {
                                if (allTemporaryBlocks[i] == getBlockAsBlock())
                                {
                                    allTemporaryBlocks.removeAt(i);
                                }
                            }
                        }
                    } else if (numberOfClickedButtons < 2) {
                        setBackgroundResource(R.drawable.square);
                        numberOfClickedButtons += 1;
                        setIsButtonPressedDown(true);
                        setIsClicked(true);
                        if (getBlockAsBlock() != null)
                        {
                            allTemporaryBlocks.add(getBlockAsBlock()!!);
                        }
                    }
                }
                setBackgroundResource(R.drawable.pin)
            }

            parentLayout.addView(buttonUp)
            parentLayout.addView(childLayout)

            childLayout.addView(microFirstBlock);
            childLayout.addView(editText);
            childLayout.addView(buttonDown)


            val constraintSet = ConstraintSet().apply {
                clone(parentLayout)
                connect(buttonUp.id, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                connect(buttonUp.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
            }

            val newConstraintSet = ConstraintSet().apply {
                clone(childLayout)
                connect(editText.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                connect(editText.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                connect(editText.id, ConstraintSet.LEFT, childLayout.id, ConstraintSet.LEFT);

                if (microFirstBlock != null)
                {
                    connect(microFirstBlock.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                    connect(microFirstBlock.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                    connect(microFirstBlock.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT);
                }

                connect(buttonDown.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP)
                connect(buttonDown.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM)
                connect(buttonDown.id, ConstraintSet.START, childLayout.id, ConstraintSet.START)
                connect(buttonDown.id, ConstraintSet.END, childLayout.id, ConstraintSet.END)
            }

            newConstraintSet.applyTo(childLayout)
            constraintSet.applyTo(parentLayout)
        }
        else if (getPinsOfBlock() == 0)
        {
            parentLayout.addView(childLayout)

            childLayout.setBackgroundResource(R.drawable.button_border);
            childLayout.addView(microFirstBlock);
            childLayout.addView(editText);

            val constraintSet = ConstraintSet().apply {
                clone(childLayout)

                connect(editText.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                connect(editText.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                connect(editText.id, ConstraintSet.LEFT, childLayout.id, ConstraintSet.LEFT);

                if (microFirstBlock != null)
                {
                    connect(microFirstBlock.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                    connect(microFirstBlock.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                    connect(microFirstBlock.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT);
                }
            }

            constraintSet.applyTo(childLayout);
        }

        setBlockAsBlock(parentLayout);
    }


}

class DirectAssignment(coordinateOfBlock: Coordinate = Coordinate(0,0),
                       sizeOfBlock: Size = Size(130, 180),
                       pinsOfBlock: Int = 2,
                       symbolOfOperation: String = "=",
                       isClicked: Boolean = false,
                       blockAsBlock: ConstraintLayout? = null,
                       isButtonPressedUp: Boolean = false,
                       isButtonPressedDown: Boolean = false) :
    AssignmentOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation,
        isClicked,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown), anyFinalBlock
{

}

class Variable(coordinateOfBlock: Coordinate = Coordinate(0, 0),
               sizeOfBlock: Size = Size(130, 180),
               pinsOfBlock: Int = 2,
               isClicked: Boolean = false,
               blockAsBlock: ConstraintLayout? = null,
               isButtonPressedUp: Boolean = false,
               isButtonPressedDown: Boolean = false) :
    BlockUI(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        isClicked,
        blockAsBlock,
        isButtonPressedUp,
        isButtonPressedDown), anyFinalBlock
{
    override fun makeBlockAsBlock(context: Context)
    {
        val parentLayout = ConstraintLayout(context).apply {
            id = ConstraintLayout.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                (getSizeOfBlock().getWidth() * context.resources.displayMetrics.density).toInt(),
                (getSizeOfBlock().getHeight() * context.resources.displayMetrics.density).toInt()
            ).apply {
                topMargin = (-20 * context.resources.displayMetrics.density).toInt();
            }
            setBackgroundResource(R.drawable.empty_element)
        }

        val childLayout = ConstraintLayout(context).apply {
            id = ConstraintLayout.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                (getSizeOfBlock().getWidth() * context.resources.displayMetrics.density).toInt(),
                ((getSizeOfBlock().getHeight() - 20) * context.resources.displayMetrics.density).toInt()
            ).apply {
                bottomToBottom = ConstraintSet.PARENT_ID
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
            }
            setBackgroundResource(R.drawable.block_border)
        }

        val editText = EditText(context).apply {
            id = EditText.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                ((getSizeOfBlock().getWidth()*0.4).toInt() * context.resources.displayMetrics.density).toInt(),
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomToBottom = ConstraintSet.PARENT_ID
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
                topToTop = ConstraintSet.PARENT_ID
            }
            hint = "Var"
            inputType = InputType.TYPE_CLASS_TEXT
            setTextAppearance(R.style.text);
            setBackgroundResource(R.drawable.substrate);
        }

        if (getPinsOfBlock() == 2)
        {

            val buttonUp = Button(context).apply {
                id = Button.generateViewId()
                layoutParams = ConstraintLayout.LayoutParams(
                    (20 * context.resources.displayMetrics.density).toInt(),
                    (20 * context.resources.displayMetrics.density).toInt()
                ).apply {
                    topMargin = (-20 * context.resources.displayMetrics.density).toInt()
                    startToStart = ConstraintSet.PARENT_ID
                    endToEnd = ConstraintSet.PARENT_ID
                }
                setOnClickListener{
                    if (getIsButtonPressedUp()) {
                        setBackgroundResource(R.drawable.pin)
                        numberOfClickedButtons -= 1;
                        setIsButtonPressedUp(false);
                        setIsClicked(false);
                        if (allTemporaryBlocks.size > 0)
                        {
                            for (i in allTemporaryBlocks.indices)
                            {
                                if (allTemporaryBlocks[i] == getBlockAsBlock())
                                {
                                    allTemporaryBlocks.removeAt(i);
                                }
                            }
                        }
                    } else if (numberOfClickedButtons < 2){
                        setBackgroundResource(R.drawable.square);
                        numberOfClickedButtons += 1;
                        setIsButtonPressedUp(true);
                        setIsClicked(true);
                        if (getBlockAsBlock() != null)
                        {
                            allTemporaryBlocks.add(getBlockAsBlock()!!);
                        }
                    }
                }
                setBackgroundResource(R.drawable.pin)
            }

            val buttonDown = Button(context).apply {
                id = Button.generateViewId()
                layoutParams = ConstraintLayout.LayoutParams(
                    (20 * context.resources.displayMetrics.density).toInt(),
                    (20 * context.resources.displayMetrics.density).toInt()
                ).apply {
                    topToTop = ConstraintSet.PARENT_ID
                    bottomToBottom = ConstraintSet.PARENT_ID
                    startToStart = ConstraintSet.PARENT_ID
                    endToEnd = ConstraintSet.PARENT_ID
                    verticalBias = 1.0f
                }
                setOnClickListener{
                    if (getIsButtonPressedDown()) {
                        setBackgroundResource(R.drawable.pin)
                        numberOfClickedButtons -= 1;
                        setIsButtonPressedDown(false);
                        setIsClicked(false);
                        if (allTemporaryBlocks.size > 0)
                        {
                            for (i in allTemporaryBlocks.indices)
                            {
                                if (allTemporaryBlocks[i] == getBlockAsBlock())
                                {
                                    allTemporaryBlocks.removeAt(i);
                                }
                            }
                        }
                    } else if (numberOfClickedButtons < 2) {
                        setBackgroundResource(R.drawable.square);
                        numberOfClickedButtons += 1;
                        setIsButtonPressedDown(true);
                        setIsClicked(true);
                        if (getBlockAsBlock() != null)
                        {
                            allTemporaryBlocks.add(getBlockAsBlock()!!);
                        }
                    }
                }
                setBackgroundResource(R.drawable.pin)
            }

            parentLayout.addView(buttonUp)
            childLayout.addView(editText)
            childLayout.addView(buttonDown)
            parentLayout.addView(childLayout)

            val constraintSet = ConstraintSet().apply {
                clone(parentLayout)
                connect(buttonUp.id, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                connect(buttonUp.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START)

                connect(editText.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM)
                connect(editText.id, ConstraintSet.LEFT, childLayout.id, ConstraintSet.LEFT)
                connect(editText.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT)
                connect(editText.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP)

                connect(buttonDown.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP)
                connect(buttonDown.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM)
                connect(buttonDown.id, ConstraintSet.START, childLayout.id, ConstraintSet.START)
                connect(buttonDown.id, ConstraintSet.END, childLayout.id, ConstraintSet.END)
            }

            constraintSet.applyTo(parentLayout)
        }
        else if (getPinsOfBlock() == 0)
        {
            childLayout.setBackgroundResource(R.drawable.button_border);
            childLayout.addView(editText)
            parentLayout.addView(childLayout)



            val constraintSet = ConstraintSet().apply {
                clone(parentLayout)
                connect(editText.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM)
                connect(editText.id, ConstraintSet.LEFT, childLayout.id, ConstraintSet.LEFT)
                connect(editText.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT)
                connect(editText.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP)
            }

            constraintSet.applyTo(parentLayout)
        }

        setBlockAsBlock(parentLayout);
    }


}