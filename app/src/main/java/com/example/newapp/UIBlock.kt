package uiblock

import android.content.Context
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import com.example.newapp.R


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

enum class Side
{
    Left,
    Right,
    Down,
    Up,
    Center,
    UpRight,
    UpLeft,
    DownRight,
    DownLeft,
    Null
}

abstract class ElementInBlock(private var size: Size,
                              private var coordinate: Coordinate,
                              private var side: Side)
{
    fun setElementSize(newSize: Size)
    {
        size = newSize;
    }

    fun setElementCoordinate(newCoordinate: Coordinate)
    {
        coordinate = newCoordinate;
    }

    fun setElementSide(newSide: Side)
    {
        side = newSide;
    }

    fun getElementSize() : Size
    {
        return size;
    }

    fun getElementCoordinate() : Coordinate
    {
        return coordinate;
    }

    fun getElementSide() : Side
    {
        return side;
    }

}

open class Hole(size: Size, coordinate: Coordinate, side: Side) : ElementInBlock(size, coordinate, side)
{

}

open class Field(size: Size, coordinate: Coordinate, side: Side) : ElementInBlock(size, coordinate, side)
{

}

open class Pin(size: Size = Size(20, 20), coordinate: Coordinate, side: Side) : ElementInBlock(size, coordinate, side)
{
    public fun makePin(context: Context) : Button
    {
        val newButton = Button(context);
        newButton.setBackgroundResource(R.drawable.pin);
        newButton.id = Button.generateViewId();

        return newButton;
    }

    /*public fun makePin(context: Context) : Pin
    {
        button = Button(context);
        button?.layoutParams?.height = (getElementSize().getHeight() * context.getResources().getDisplayMetrics().density).toInt();
        button?.layoutParams?.width = (getElementSize().getWidth() * context.getResources().getDisplayMetrics().density).toInt();
        button?.setBackgroundResource(R.drawable.pin);
        //Дополнить данными из Pin
        return this;
    }

    public fun makeButton(context: Context) : Button
    {
        button = Button(context);
        button?.layoutParams?.height = (getElementSize().getHeight() * context.getResources().getDisplayMetrics().density).toInt();
        button?.layoutParams?.width = (getElementSize().getWidth() * context.getResources().getDisplayMetrics().density).toInt();
        button?.setBackgroundResource(R.drawable.pin);
        //Дополнить данными из Pin
        return button as Button;

    }*/

}

open class InputFields(private var listOfFields: List<Field>)
{
    public fun getInputFields() : List<Field>
    {
        return listOfFields;
    }

}

open class Holes(private var listOfHoles: List<Hole>)
{
    public fun getListOfHoles() : List<Hole>
    {
        return listOfHoles;
    }

}

open class Pins(private var listOfPins: MutableList<Pin>)
{
    public fun getListOfPins() : List<Pin>
    {
        return listOfPins;
    }

    public fun addTwoPins(firstPin: Pin, secondPin: Pin)
    {
        listOfPins.add(firstPin);
        listOfPins.add(secondPin);
    }
}


class BlockUI(private var coordinateOfBlock: Coordinate = Coordinate(0, 0),
                       private var sizeOfBlock: Size = Size(130, 180),
                       private var pinsOfBlock: Pins = Pins(mutableListOf(Pin(Size(20, 20),
                           Coordinate(0, 0), Side.Up),
                           Pin(Size(20, 20),
                               Coordinate(0, 0), Side.Down))))
{
    public fun getCoordinateOfBlock() : Coordinate // не забыть вернуть abstract у класса
    {
        return coordinateOfBlock;
    }

    public fun getSizeOfBlock() : Size
    {
        return sizeOfBlock;
    }

    public fun getPinsOfBlock() : Pins
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

    public fun setPinsOfBlock(newPinsOfBlock: Pins) : Unit
    {
        pinsOfBlock = newPinsOfBlock;
    }

    public fun makeDefaultBlock(context: Context) : ConstraintLayout
    {
        val parentLayout = ConstraintLayout(context).apply {
            id = ConstraintLayout.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                (sizeOfBlock.getWidth() * context.resources.displayMetrics.density).toInt(),
                (sizeOfBlock.getHeight() * context.resources.displayMetrics.density).toInt()
            )
            setBackgroundResource(R.drawable.empty_element)
        }

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

        val childLayout = ConstraintLayout(context).apply {
            id = ConstraintLayout.generateViewId()
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
        childLayout.addView(buttonDown)
        parentLayout.addView(childLayout)

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

        return parentLayout;
    }

    /*public fun makeUsualBlock(context: Context) : ConstraintLayout
    {
        val newBlock = ConstraintLayout(context);

        newBlock.id = ConstraintLayout.generateViewId();
        newBlock.setBackgroundResource(R.drawable.block_border);

        val layoutParameters = MarginLayoutParams(sizeOfBlock.fromDpToPx(context, sizeOfBlock.getWidth()),
            sizeOfBlock.fromDpToPx(context, sizeOfBlock.getHeight()));

        layoutParameters.setMargins(
            (10 * context.resources.displayMetrics.density).toInt(),
            (10 * context.resources.displayMetrics.density).toInt(),
            (10 * context.resources.displayMetrics.density).toInt(),
            (10 * context.resources.displayMetrics.density).toInt());
        newBlock.layoutParams = layoutParameters;

        if (getPinsOfBlock().getListOfPins().isNotEmpty())
        {
            val firstPin = getPinsOfBlock().getListOfPins()[0];
            val secondPin = getPinsOfBlock().getListOfPins()[1];
            val firstButton = firstPin.makePin(context);
            val secondButton = secondPin.makePin(context);

            val set = ConstraintSet();
            set.clone(newBlock);

            if (firstPin.getElementSide() == Side.Up)
            {
                val layoutParams = ConstraintLayout.LayoutParams(
                    (firstPin.getElementSize().getWidth() *
                            context.resources.displayMetrics.density).toInt(),
                    (firstPin.getElementSize().getHeight() *
                            context.resources.displayMetrics.density).toInt()
                )

                layoutParams.topMargin = (-20 * context.resources.displayMetrics.density).toInt();

                newBlock.addView(firstButton);

                layoutParams.width = (firstPin.getElementSize().getWidth() * context.resources.displayMetrics.density).toInt();
                layoutParams.height = (firstPin.getElementSize().getHeight() * context.resources.displayMetrics.density).toInt();


                firstButton.layoutParams = layoutParams;

                set.connect(firstButton.id, ConstraintSet.END, newBlock.id, ConstraintSet.END);
                set.connect(firstButton.id, ConstraintSet.START, newBlock.id, ConstraintSet.START);

                set.applyTo(newBlock);
            }
            else
            {
                val layoutParams = ConstraintLayout.LayoutParams(
                    (secondPin.getElementSize().getWidth() *
                            context.resources.displayMetrics.density).toInt(),
                    (secondPin.getElementSize().getHeight() *
                            context.resources.displayMetrics.density).toInt()
                )

                layoutParams.topMargin = (-20 * context.resources.displayMetrics.density).toInt();



                newBlock.addView(secondButton);

                layoutParams.width = (secondPin.getElementSize().getWidth() * context.resources.displayMetrics.density).toInt();
                layoutParams.height = (secondPin.getElementSize().getHeight() * context.resources.displayMetrics.density).toInt();

                secondButton.layoutParams = layoutParams;

                set.connect(secondButton.id, ConstraintSet.END, newBlock.id, ConstraintSet.END);
                set.connect(secondButton.id, ConstraintSet.START, newBlock.id, ConstraintSet.START);

                set.applyTo(newBlock);

            }
        }

        return newBlock;


        /*val newBlock = ConstraintLayout(context);
        val layoutParameters = MarginLayoutParams(sizeOfBlock.fromDpToPx(context, sizeOfBlock.getWidth()),
            sizeOfBlock.fromDpToPx(context, sizeOfBlock.getHeight()));

        layoutParameters.setMargins(
            (10 * context.resources.displayMetrics.density).toInt(),
            (10 * context.resources.displayMetrics.density).toInt(),
            (10 * context.resources.displayMetrics.density).toInt(),
            (10 * context.resources.displayMetrics.density).toInt());
        newBlock.layoutParams = layoutParameters;

        newBlock.id = ConstraintLayout.generateViewId();

        newBlock.setBackgroundResource(R.drawable.empty_element);

        newBlock.layoutParams.height = sizeOfBlock.fromDpToPx(context, sizeOfBlock.getHeight());
        newBlock.layoutParams.width = sizeOfBlock.fromDpToPx(context, sizeOfBlock.getWidth());

        if (pinsOfBlock.getListOfPins().size == 2)
        {
            val temporaryList: List<Pin> = pinsOfBlock.getListOfPins();
            val temporaryButtonFirst = temporaryList[0].makePin(context);
            val temporaryButtonSecond = temporaryList[1].makePin(context);

            val set = ConstraintSet();

            val newSubBlock = ConstraintLayout(context);
            newSubBlock.id = ConstraintLayout.generateViewId();

            if (newSubBlock.layoutParams == null) {
                newSubBlock.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

            val temporaryWidth = newBlock.layoutParams.width;
            val temporaryHeight = newBlock.layoutParams.height

            newSubBlock.layoutParams.width = temporaryWidth;
            newSubBlock.layoutParams.height = temporaryHeight -
                    (20 * context.resources.displayMetrics.density).toInt();
            newSubBlock.setBackgroundResource(R.drawable.block_border);

            set.connect(newSubBlock.id, ConstraintSet.BOTTOM, newBlock.id, ConstraintSet.BOTTOM);
            set.connect(newSubBlock.id, ConstraintSet.LEFT, newBlock.id, ConstraintSet.LEFT);
            set.connect(newSubBlock.id, ConstraintSet.RIGHT, newBlock.id, ConstraintSet.RIGHT);

            newBlock.addView(newSubBlock);

            if (pinsOfBlock.getListOfPins()[0].getElementSide() == Side.Up)
            {
                set.connect(temporaryButtonFirst.id, ConstraintSet.END, newBlock.id, ConstraintSet.END);
                set.connect(temporaryButtonFirst.id, ConstraintSet.START, newBlock.id, ConstraintSet.START);

                newBlock.addView(temporaryButtonFirst);

                val layoutParametrs = temporaryButtonFirst.layoutParams as ConstraintLayout.LayoutParams;
                layoutParametrs.setMargins(0, (-20 * context.resources.displayMetrics.density).toInt(), 0, 0);
                temporaryButtonFirst.layoutParams = layoutParametrs;

                        set.connect(temporaryButtonSecond.id, ConstraintSet.END, newSubBlock.id, ConstraintSet.END);
                set.connect(temporaryButtonSecond.id, ConstraintSet.START, newSubBlock.id, ConstraintSet.START);
                set.connect(temporaryButtonSecond.id, ConstraintSet.BOTTOM, newSubBlock.id, ConstraintSet.BOTTOM);
                set.connect(temporaryButtonSecond.id, ConstraintSet.TOP, newSubBlock.id, ConstraintSet.TOP);

                set.setVerticalBias(temporaryButtonSecond.id, 1.11F);

                newSubBlock.addView(temporaryButtonSecond);
            }
            else
            {
                set.connect(temporaryButtonSecond.id, ConstraintSet.END, newBlock.id, ConstraintSet.END);
                set.connect(temporaryButtonSecond.id, ConstraintSet.START, newBlock.id, ConstraintSet.START);

                newBlock.addView(temporaryButtonSecond);

                val layoutParametrs = temporaryButtonSecond.layoutParams as ConstraintLayout.LayoutParams;
                layoutParametrs.setMargins(0, (-20 * context.resources.displayMetrics.density).toInt(), 0, 0);
                temporaryButtonSecond.layoutParams = layoutParametrs;

                set.connect(temporaryButtonFirst.id, ConstraintSet.END, newSubBlock.id, ConstraintSet.END);
                set.connect(temporaryButtonFirst.id, ConstraintSet.START, newSubBlock.id, ConstraintSet.START);
                set.connect(temporaryButtonFirst.id, ConstraintSet.BOTTOM, newSubBlock.id, ConstraintSet.BOTTOM);
                set.connect(temporaryButtonFirst.id, ConstraintSet.TOP, newSubBlock.id, ConstraintSet.TOP);

                set.setVerticalBias(temporaryButtonFirst.id, 1.11F);

                newSubBlock.addView(temporaryButtonFirst);
            }
        }


        return newBlock;*/



    }*/

    /*public fun makeConstraintLayout(context: Context) : ConstraintLayout
    {
        val newBlock = ConstraintLayout(context);
        newBlock.layoutParams.height = (sizeOfBlock.getHeight() * context.getResources().getDisplayMetrics().density).toInt();
        newBlock.layoutParams.width = (sizeOfBlock.getWidth() * context.getResources().getDisplayMetrics().density).toInt();

        if (pinsOfBlock.getListOfPins().size == 2)
        {
            newBlock.addView(pinsOfBlock.getListOfPins()[0].makeButton(context));
            newBlock.addView(pinsOfBlock.getListOfPins()[1].makeButton(context))
        }
        else
        {

        }

        //Дополнить поля BlockUi

        return newBlock;
    }

    public fun makeBlock(context: Context) : BlockUI
    {
        val newBlock = ConstraintLayout(context);
        newBlock.layoutParams.height = (sizeOfBlock.getHeight() * context.getResources().getDisplayMetrics().density).toInt();
        newBlock.layoutParams.width = (sizeOfBlock.getWidth() * context.getResources().getDisplayMetrics().density).toInt();

        if (pinsOfBlock.getListOfPins().size == 2)
        {
            newBlock.addView(pinsOfBlock.getListOfPins()[0].makeButton(context));
            newBlock.addView(pinsOfBlock.getListOfPins()[1].makeButton(context))
        }
        else
        {

        }

        //Дополнить поля BlockUi

        return this;
    }*/
}