package com.example.newapp

class Size(
    private var height: Int,
    private var width: Int
) {

    public fun setHeight(newHeight: Int) : Unit
    {
        height = newHeight;
    }

    public fun setWidth(newWidth: Int) : Unit
    {
        width = newWidth;
    }

    public fun setSize(newHeight: Int, newWidth: Int) : Unit
    {
        height = newHeight;
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
    private var y: Int
) {

    public fun setX(newX: Int) : Unit
    {
        x = newX;
    }

    public fun setY(newY: Int) : Unit
    {
        y = newY;
    }

    public fun setCoordinates(newX: Int, newY: Int) : Unit
    {
        x = newX;
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

class Holes (private var numberOfRightHoles: Int,
             private var numberOfLeftHoles: Int,
             private var numberOfDownHoles: Int)
{
    public fun setNumberOfRightHoles(newNumberOfRightHoles: Int) : Unit
    {
        numberOfRightHoles = newNumberOfRightHoles;
    }

    public fun setNumberOfLeftHoles(newNumberOfLeftHoles: Int) : Unit
    {
        numberOfLeftHoles = newNumberOfLeftHoles;
    }

    public fun setNumberOfDownHoles(newNumberOfDownHoles: Int) : Unit
    {
        numberOfDownHoles = newNumberOfDownHoles;
    }

    public fun setAllHoles(
        newNumberOfLeftHoles: Int,
        newNumberOfRightHoles: Int,
        newNumberOfDownHoles: Int) : Unit
    {
        numberOfRightHoles = newNumberOfRightHoles;
        numberOfLeftHoles = newNumberOfLeftHoles;
        numberOfDownHoles = newNumberOfDownHoles;
    }

    public fun getNumberOfRightHoles() : Int
    {
        return numberOfRightHoles;
    }

    public fun getNumberOfLeftHoles() : Int
    {
        return numberOfLeftHoles;
    }

    public fun getNumberOfDownHoles() : Int
    {
        return numberOfDownHoles;
    }
}

abstract class BlockUI(private var coordinateOfBlock: Coordinate,
                       private var sizeOfBlock: Size,
                       private var holesOfBlock: Holes)
{

}

