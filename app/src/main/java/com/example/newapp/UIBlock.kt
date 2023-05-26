package uiblock

import android.content.Context
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.newapp.R

var numberOfClickedButtons = 0;
val allTemporaryBlocks = mutableListOf<ConstraintLayout>();
class Size(
    private var height: Int,
    private var width: Int)
{
    fun setHeight(newHeight: Int) : Unit
    {
        height = newHeight;
    }

    fun setWidth(newWidth: Int) : Unit
    {
        width = newWidth;
    }

    fun getHeight(): Int
    {
        return height;
    }

    fun getWidth(): Int
    {
        return width;
    }

    fun fromDpToPx(context: Context, dp: Int) : Int
    {
        return (dp * context.resources.displayMetrics.density).toInt();
    }
}

class Coordinate(
    private var x: Int,
    private var y: Int)
{

    fun setX(newX: Int) : Unit
    {
        x = newX;
    }

    fun setY(newY: Int) : Unit
    {
        y = newY;
    }

    fun getX(): Int
    {
        return x;
    }

    fun getY(): Int
    {
        return y;
    }
}

open class BlockUI(private var coordinateOfBlock: Coordinate = Coordinate(0, 0),
                   private var sizeOfBlock: Size = Size(130, 180),
                   private var pinsOfBlock: Int = 2, private var isClicked: Boolean = false,
                   private var blockAsBlock: ConstraintLayout? = null,
                   private var isButtonPressedUp: Boolean = false,
                   private var isButtonPressedDown: Boolean = false)
{
    public fun getCoordinateOfBlock() : Coordinate
    {
        return coordinateOfBlock;
    }

    public fun getSizeOfBlock() : Size
    {
        return sizeOfBlock;
    }

    public fun getPinsOfBlock() : Int
    {
        return pinsOfBlock;
    }

    public fun setCoordinateOfBlock(newCoordinateOfBlock: Coordinate) : Unit
    {
        coordinateOfBlock = newCoordinateOfBlock;
    }

    public fun setSizeOfBlock(newSizeOfBlock: Size) : Unit
    {
        sizeOfBlock = newSizeOfBlock;
    }

    public fun setPinsOfBlock(newPinsOfBlock: Int) : Unit
    {
        pinsOfBlock = newPinsOfBlock;
    }

    public fun setIsClicked(newIsClicked: Boolean)
    {
        isClicked = newIsClicked;
    }

    public fun getIsClicked() : Boolean
    {
        return isClicked;
    }

    public fun getIsButtonPressedDown() : Boolean
    {
        return isButtonPressedDown
    }

    public fun getIsButtonPressedUp() : Boolean
    {
        return isButtonPressedUp
    }

    public fun setIsButtonPressedDown(newButtonPressedDown: Boolean)
    {
        isButtonPressedDown = newButtonPressedDown;
    }

    public fun setIsButtonPressedUp(newButtonPressedUp: Boolean)
    {
        isButtonPressedUp = newButtonPressedUp;
    }

    public open fun makeBlockAsBlock(context: Context)
    {
        val parentLayout = ConstraintLayout(context).apply {
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                (sizeOfBlock.getWidth() * context.resources.displayMetrics.density).toInt(),
                (sizeOfBlock.getHeight() * context.resources.displayMetrics.density).toInt()
            ).apply {
                leftMargin = (10 * context.resources.displayMetrics.density).toInt();
                rightMargin = (10 * context.resources.displayMetrics.density).toInt();
            }
            setBackgroundResource(R.drawable.empty_element)
        }

        val childLayout = ConstraintLayout(context).apply {
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                (sizeOfBlock.getWidth() * context.resources.displayMetrics.density).toInt(),
                ((sizeOfBlock.getHeight() - 20) * context.resources.displayMetrics.density).toInt()
            ).apply {
                bottomToBottom = ConstraintSet.PARENT_ID
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
            }
            setBackgroundResource(R.drawable.block_border)
        }

        if (pinsOfBlock == 2)
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
                    if (isButtonPressedUp) {
                        setBackgroundResource(R.drawable.pin)
                        numberOfClickedButtons -= 1;
                        isClicked = false;
                        isButtonPressedUp = false;
                        if (allTemporaryBlocks.size > 0)
                        {
                            for (i in allTemporaryBlocks.indices)
                            {
                                if (allTemporaryBlocks[i] == blockAsBlock)
                                {
                                    allTemporaryBlocks.removeAt(i);
                                }
                            }
                        }
                    } else if (numberOfClickedButtons < 2){
                        setBackgroundResource(R.drawable.square);
                        numberOfClickedButtons += 1;
                        isClicked = true;
                        isButtonPressedUp = true;
                        if (blockAsBlock != null)
                        {
                            allTemporaryBlocks.add(blockAsBlock!!);
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
                setBackgroundResource(R.drawable.pin)
            }

            parentLayout.addView(childLayout)
            parentLayout.addView(buttonUp)

            childLayout.addView(buttonDown)

            val constraintSet = ConstraintSet().apply {
                clone(parentLayout)
                connect(buttonUp.id, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                connect(buttonUp.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START)

                connect(buttonDown.id, ConstraintSet.TOP, childLayout.id, ConstraintSet.TOP)
                connect(buttonDown.id, ConstraintSet.BOTTOM, childLayout.id, ConstraintSet.BOTTOM)
                connect(buttonDown.id, ConstraintSet.START, childLayout.id, ConstraintSet.START)
                connect(buttonDown.id, ConstraintSet.END, childLayout.id, ConstraintSet.END)
            }

            constraintSet.applyTo(parentLayout)
        }
        else if (pinsOfBlock == 0)
        {
            childLayout.setBackgroundResource(R.drawable.button_border);
            parentLayout.addView(childLayout)

            val constraintSet = ConstraintSet().apply {
                clone(parentLayout)
            }

            constraintSet.applyTo(parentLayout)
        }

        blockAsBlock = parentLayout;
    }

    public fun setBlockAsBlock(newBlockAsBlock: ConstraintLayout)
    {
        blockAsBlock = newBlockAsBlock;
    }

    public fun getBlockAsBlock() : ConstraintLayout?
    {
        return blockAsBlock
    }
}