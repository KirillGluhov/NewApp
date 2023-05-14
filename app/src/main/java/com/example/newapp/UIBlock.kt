package uiblock

import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.newapp.R
import com.google.android.material.internal.ViewUtils.dpToPx

class Size(
    private var height: Int,
    private var width: Int)
{
    public fun setHeight(newHeight: Int) : Unit
    {
        height = newHeight;
    }

    public fun setWidth(newWidth: Int) : Unit
    {
        width = newWidth;
    }

    public fun getHeight(): Int
    {
        return height;
    }

    public fun getWidth(): Int
    {
        return width;
    }
}

class Coordinate(
    private var x: Int,
    private var y: Int)
{

    public fun setX(newX: Int) : Unit
    {
        x = newX;
    }

    public fun setY(newY: Int) : Unit
    {
        y = newY;
    }

    public fun getX(): Int
    {
        return x;
    }

    public fun getY(): Int
    {
        return y;
    }
}

enum class Sides
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
                              private var side: Sides)
{
    public fun setElementSize(newSize: Size)
    {
        size = newSize;
    }

    public fun setElementCoordinate(newCoordinate: Coordinate)
    {
        coordinate = newCoordinate;
    }

    public fun setElementSide(newSide: Sides)
    {
        side = newSide;
    }

    public fun getElementSize() : Size
    {
        return size;
    }

    public fun getElementCoordinate() : Coordinate
    {
        return coordinate;
    }

    public fun getElementSide() : Sides
    {
        return side;
    }

}

open class Hole(size: Size, coordinate: Coordinate, side: Sides) : ElementInBlock(size, coordinate, side)
{

}

open class Field(size: Size, coordinate: Coordinate, side: Sides) : ElementInBlock(size, coordinate, side)
{

}

open class Pin(size: Size = Size(20, 20), coordinate: Coordinate, side: Sides) : ElementInBlock(size, coordinate, side)
{
    private var button: Button? = null;

    public fun makePin(context: Context) : Pin
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

    }

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


abstract class BlockUI(private var coordinateOfBlock: Coordinate,
                       private var sizeOfBlock: Size,
                       private var holesOfBlock: Holes,
                       private var inputFieldsOfBlock: InputFields,
                       private var pinsOfBlock: Pins,
                       private var symbolOfOperation: String)
{
    public fun getCoordinateOfBlock() : Coordinate
    {
        return coordinateOfBlock;
    }

    public fun getSizeOfBlock() : Size
    {
        return sizeOfBlock;
    }

    public fun getHolesOfBlock() : Holes
    {
        return holesOfBlock;
    }

    public fun getInputFieldsOfBlock() : InputFields
    {
        return inputFieldsOfBlock;
    }

    public fun getPinsOfBlock() : Pins
    {
        return pinsOfBlock;
    }

    public fun getSymbolOfOperation() : String
    {
        return symbolOfOperation;
    }

    public fun setCoordinateOfBlock(newCoordinateOfBlock: Coordinate) : Unit
    {
        coordinateOfBlock = newCoordinateOfBlock;
    }

    public fun setSizeOfBlock(newSizeOfBlock: Size) : Unit
    {
        sizeOfBlock = newSizeOfBlock;
    }

    public fun setHolesOfBlock(newHolesOfBlock: Holes) : Unit
    {
        holesOfBlock = newHolesOfBlock;
    }

    public fun setInputFieldsOfBlock(newInputFieldsOfBlock: InputFields) : Unit
    {
        inputFieldsOfBlock = newInputFieldsOfBlock;
    }

    public fun setPinsOfBlock(newPinsOfBlock: Pins) : Unit
    {
        pinsOfBlock = newPinsOfBlock;
    }

    public fun setSymbolOfOperation(newSymbolOfOperation: String) : Unit
    {
        symbolOfOperation = newSymbolOfOperation;
    }

    public fun makeConstraintLayout(context: Context) : ConstraintLayout
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
    }
}