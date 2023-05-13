package uiblockvarioustypesofblocks

import uiblock.*

abstract class Function(coordinateOfBlock: Coordinate,
                        sizeOfBlock: Size,
                        holesOfBlock: Holes,
                        inputFieldsOfBlock: InputFields,
                        pinsOfBlock: Pins,
                        symbolOfOperation: String) :
    BlockUI(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation)
{

}

abstract class Operator(coordinateOfBlock: Coordinate,
                        sizeOfBlock: Size,
                        holesOfBlock: Holes,
                        inputFieldsOfBlock: InputFields,
                        pinsOfBlock: Pins,
                        symbolOfOperation: String) :
    BlockUI(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation)
{

}

abstract class BinaryOperator(coordinateOfBlock: Coordinate,
                              sizeOfBlock: Size,
                              holesOfBlock: Holes,
                              inputFieldsOfBlock: InputFields,
                              pinsOfBlock: Pins,
                              symbolOfOperation: String) :
    Operator(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation)
{

}

abstract class UnaryOperator(coordinateOfBlock: Coordinate,
                             sizeOfBlock: Size,
                             holesOfBlock: Holes,
                             inputFieldsOfBlock: InputFields,
                             pinsOfBlock: Pins,
                             symbolOfOperation: String) :
    Operator(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation)
{

}

abstract class TernaryOperator(coordinateOfBlock: Coordinate,
                               sizeOfBlock: Size,
                               holesOfBlock: Holes,
                               inputFieldsOfBlock: InputFields,
                               pinsOfBlock: Pins,
                               symbolOfOperation: String) :
    Operator(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation)
{

}

abstract class AssignmentOperator(coordinateOfBlock: Coordinate,
                                  sizeOfBlock: Size,
                                  holesOfBlock: Holes,
                                  inputFieldsOfBlock: InputFields,
                                  pinsOfBlock: Pins,
                                  symbolOfOperation: String) :
    BinaryOperator(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation)
{

}

abstract class ArithmeticOperator(coordinateOfBlock: Coordinate,
                                  sizeOfBlock: Size,
                                  holesOfBlock: Holes,
                                  inputFieldsOfBlock: InputFields,
                                  pinsOfBlock: Pins,
                                  symbolOfOperation: String) :
    BinaryOperator(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation)
{

}

interface anyFinalBlock{

}

enum class FinalClasses
{
    Addition,
    Substraction,
    Modulo,
    Division,
    Multiplication,
    Initialization,
    DirectAssignment,
    Variable
}

class Addition(coordinateOfBlock: Coordinate,
                                  sizeOfBlock: Size,
                                  holesOfBlock: Holes,
                                  inputFieldsOfBlock: InputFields,
                                  pinsOfBlock: Pins,
                                  symbolOfOperation: String) :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class Substraction(coordinateOfBlock: Coordinate,
                      sizeOfBlock: Size,
                      holesOfBlock: Holes,
                      inputFieldsOfBlock: InputFields,
                      pinsOfBlock: Pins,
                      symbolOfOperation: String) :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class Modulo(coordinateOfBlock: Coordinate,
                        sizeOfBlock: Size,
                        holesOfBlock: Holes,
                        inputFieldsOfBlock: InputFields,
                        pinsOfBlock: Pins,
                        symbolOfOperation: String) :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class Division(coordinateOfBlock: Coordinate,
                      sizeOfBlock: Size,
                      holesOfBlock: Holes,
                      inputFieldsOfBlock: InputFields,
                      pinsOfBlock: Pins,
                      symbolOfOperation: String) :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class Multiplication(coordinateOfBlock: Coordinate,
                        sizeOfBlock: Size,
                        holesOfBlock: Holes,
                        inputFieldsOfBlock: InputFields,
                        pinsOfBlock: Pins,
                        symbolOfOperation: String) :
    ArithmeticOperator(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class Initialization(coordinateOfBlock: Coordinate,
                             sizeOfBlock: Size,
                             holesOfBlock: Holes,
                             inputFieldsOfBlock: InputFields,
                             pinsOfBlock: Pins,
                             symbolOfOperation: String) :
    UnaryOperator(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class DirectAssignment(coordinateOfBlock: Coordinate,
                      sizeOfBlock: Size,
                      holesOfBlock: Holes,
                      inputFieldsOfBlock: InputFields,
                      pinsOfBlock: Pins,
                      symbolOfOperation: String) :
    AssignmentOperator(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class Variable(coordinateOfBlock: Coordinate,
               sizeOfBlock: Size,
               holesOfBlock: Holes,
               inputFieldsOfBlock: InputFields,
               pinsOfBlock: Pins,
               symbolOfOperation: String) :
    BlockUI(coordinateOfBlock,
        sizeOfBlock,
        holesOfBlock,
        inputFieldsOfBlock,
        pinsOfBlock,
        symbolOfOperation), anyFinalBlock
{

}

class sideMenu(private var listOfBlocks: List<anyFinalBlock>, private var allClasses: Array<FinalClasses> = FinalClasses.values())
{
    public fun getListOfBlocks() : List<anyFinalBlock>
    {
        return listOfBlocks;
    }

    public fun getAllClasses(): Array<FinalClasses>
    {
        return allClasses;
    }

    public fun setListOfBlocks(newListOfBlocks: List<anyFinalBlock>) : Unit
    {
        listOfBlocks = newListOfBlocks;
    }

    public fun createSideMenu()
    {
        for (i in allClasses)
        {

        }

    }

}