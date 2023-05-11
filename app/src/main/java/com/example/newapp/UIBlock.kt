package com.example.newapp

class Size(
    private var height: Int = 0,
    private var width: Int = 0)
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
    private var x: Int = 0,
    private var y: Int = 0
) {

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

abstract class ElementInBlock(private var size: Size = Size(0, 0),
                              private var coordinate: Coordinate = Coordinate(0, 0),
                              private var side: Sides = Sides.Null)
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

class Hole : ElementInBlock()
{

}

class Field : ElementInBlock()
{

}

class Pin : ElementInBlock()
{

}

class InputFields(private var listOfFields: List<Field>)
{
    public fun getInputFields() : List<Field>
    {
        return listOfFields;
    }

}

class Holes(private var listOfHoles: List<Hole>)
{
    public fun getListOfHoles() : List<Hole>
    {
        return listOfHoles;
    }

}

class Pins(private var listOfPins: List<Pin>)
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

}


