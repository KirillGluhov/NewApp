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

abstract class Function(coordinateOfBlock: Coordinate,
                        sizeOfBlock: Size,
                        pinsOfBlock: Int) :
    BlockUI(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock)
{

}

abstract class Operator(coordinateOfBlock: Coordinate,
                        sizeOfBlock: Size,
                        pinsOfBlock: Int,
                        symbolOfOperation: String) :
    BlockUI(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock)
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
                              symbolOfOperation: String = "!") :
    Operator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation)
{
    override fun makeBlock(context: Context) : ConstraintLayout //вернуть abstract class
    {
        val parentLayout = ConstraintLayout(context).apply {
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                (getSizeOfBlock().getWidth() * context.resources.displayMetrics.density).toInt(),
                (getSizeOfBlock().getHeight() * context.resources.displayMetrics.density).toInt()
            )
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

        val microFirstBlock = BlockUI(Coordinate(0,0),
            Size((getSizeOfBlock().getHeight()*0.4).toInt(),
                (getSizeOfBlock().getWidth()*0.4).toInt() ),
            2).makeBlock(context);

        val microSecondBlock = BlockUI(Coordinate(0,0),
            Size((getSizeOfBlock().getHeight()*0.4).toInt(),
                (getSizeOfBlock().getWidth()*0.4).toInt() ),
            2).makeBlock(context);

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
                connect(microSecondBlock.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                connect(microSecondBlock.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                connect(microSecondBlock.id, ConstraintSet.LEFT, childLayout.id, ConstraintSet.LEFT);

                connect(textView.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM)
                connect(textView.id, ConstraintSet.LEFT, childLayout.id, ConstraintSet.LEFT)
                connect(textView.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT)
                connect(textView.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP)

                connect(microFirstBlock.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                connect(microFirstBlock.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                connect(microFirstBlock.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT);

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

                connect(microSecondBlock.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                connect(microSecondBlock.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                connect(microSecondBlock.id, ConstraintSet.LEFT, childLayout.id, ConstraintSet.LEFT);

                connect(textView.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM)
                connect(textView.id, ConstraintSet.LEFT, childLayout.id, ConstraintSet.LEFT)
                connect(textView.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT)
                connect(textView.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP)

                connect(microFirstBlock.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                connect(microFirstBlock.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                connect(microFirstBlock.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT);
            }

            constraintSet.applyTo(childLayout);
        }

        return parentLayout;
    }

}

abstract class UnaryOperator(coordinateOfBlock: Coordinate,
                             sizeOfBlock: Size,
                             pinsOfBlock: Int,
                             symbolOfOperation: String) :
    Operator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation)
{

}

abstract class TernaryOperator(coordinateOfBlock: Coordinate,
                               sizeOfBlock: Size,
                               pinsOfBlock: Int,
                               symbolOfOperation: String) :
    Operator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation)
{

}

abstract class AssignmentOperator(coordinateOfBlock: Coordinate,
                                  sizeOfBlock: Size,
                                  pinsOfBlock: Int,
                                  symbolOfOperation: String) :
    BinaryOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation)
{

}

abstract class ArithmeticOperator(coordinateOfBlock: Coordinate = Coordinate(0,0),
                              sizeOfBlock: Size = Size(130, 180),
                              pinsOfBlock: Int = 2,
                              symbolOfOperation: String = "!") :
    BinaryOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation)
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
               symbolOfOperation: String = "+") :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class Subtraction(coordinateOfBlock: Coordinate = Coordinate(0,0),
                   sizeOfBlock: Size = Size(130, 180),
                   pinsOfBlock: Int = 2,
                   symbolOfOperation: String = "-") :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class Modulo(coordinateOfBlock: Coordinate = Coordinate(0,0),
             sizeOfBlock: Size = Size(130, 180),
             pinsOfBlock: Int = 2,
             symbolOfOperation: String = "%") :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class Division(coordinateOfBlock: Coordinate = Coordinate(0,0),
               sizeOfBlock: Size = Size(130, 180),
               pinsOfBlock: Int = 2,
               symbolOfOperation: String = "/") :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class Multiplication(coordinateOfBlock: Coordinate = Coordinate(0,0),
                     sizeOfBlock: Size = Size(130, 180),
                     pinsOfBlock: Int = 2,
                     symbolOfOperation: String = "*") :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class Initialization(coordinateOfBlock: Coordinate = Coordinate(0,0),
                     sizeOfBlock: Size = Size(130, 180),
                     pinsOfBlock: Int = 2,
                     symbolOfOperation: String = "") :
    UnaryOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{
    override fun makeBlock(context: Context) : ConstraintLayout
    {
        val parentLayout = ConstraintLayout(context).apply {
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                (getSizeOfBlock().getWidth() * context.resources.displayMetrics.density).toInt(),
                (getSizeOfBlock().getHeight() * context.resources.displayMetrics.density).toInt()
            )
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

        val microFirstBlock = BlockUI(Coordinate(0,0),
            Size((getSizeOfBlock().getHeight()*0.4).toInt(),
                (getSizeOfBlock().getWidth()*0.4).toInt() ),
            2).makeBlock(context);

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

                connect(microFirstBlock.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                connect(microFirstBlock.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                connect(microFirstBlock.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT);

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

                connect(microFirstBlock.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP);
                connect(microFirstBlock.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM);
                connect(microFirstBlock.id, ConstraintSet.RIGHT, childLayout.id, ConstraintSet.RIGHT);
            }

            constraintSet.applyTo(childLayout);
        }

        return parentLayout;
    }


}

class DirectAssignment(coordinateOfBlock: Coordinate = Coordinate(0,0),
                       sizeOfBlock: Size = Size(130, 180),
                       pinsOfBlock: Int = 2,
                       symbolOfOperation: String = "=") :
    AssignmentOperator(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class Variable(coordinateOfBlock: Coordinate = Coordinate(0, 0),
               sizeOfBlock: Size = Size(130, 180),
               pinsOfBlock: Int = 2) :
    BlockUI(coordinateOfBlock,
        sizeOfBlock,
        pinsOfBlock), anyFinalBlock
{
    override fun makeBlock(context: Context) : ConstraintLayout
    {
        val parentLayout = ConstraintLayout(context).apply {
            id = ConstraintLayout.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                (getSizeOfBlock().getWidth() * context.resources.displayMetrics.density).toInt(),
                (getSizeOfBlock().getHeight() * context.resources.displayMetrics.density).toInt()
            )
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

        return parentLayout;
    }


}