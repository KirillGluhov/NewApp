package com.example.newapp

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

abstract class DirectAssigment(coordinateOfBlock: Coordinate,
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

abstract class Addition(coordinateOfBlock: Coordinate,
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
        symbolOfOperation)
{

}

abstract class Substraction(coordinateOfBlock: Coordinate,
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
        symbolOfOperation)
{

}

abstract class Modulo(coordinateOfBlock: Coordinate,
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
        symbolOfOperation)
{

}

abstract class Division(coordinateOfBlock: Coordinate,
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
        symbolOfOperation)
{

}

abstract class Multiplication(coordinateOfBlock: Coordinate,
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
        symbolOfOperation)
{

}

abstract class Initialization(coordinateOfBlock: Coordinate,
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
        symbolOfOperation)
{

}