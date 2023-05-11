package com.example.newapp

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
    Left, Right, Down, Up, Null
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

open class Pin(size: Size, coordinate: Coordinate, side: Sides) : ElementInBlock(size, coordinate, side)
{

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

open class Pins(private var listOfPins: List<Pin>)
{
    public fun getListOfPins() : List<Pin>
    {
        return listOfPins;
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
}