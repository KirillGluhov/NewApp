package com.example.newapp

import java.util.Hashtable


object Interpretate {
    private fun is_space(c: Char): Boolean {
        return c == ' '
    }

    private fun is_op(c: Char): Boolean {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '%')
    }

    private fun priority(op: Char): Int {
        return if (op == '+' || op == '-') 1 else if ((op == '*') || op == '/' || op == '%') 2 else -1
    }

    private fun process_op(values: ArrayList<Int>, op: Char) {
        val r = values[values.size - 1]
        values.removeAt(values.size - 1)
        val l = values[values.size - 1]
        values.removeAt(values.size - 1)
        when (op) {
            '+' -> values.add(l + r)
            '-' -> values.add(l - r)
            '*' -> values.add(l * r)
            '/' -> values.add(l / r)
            '%' -> values.add(l % r)
        }
    }

    fun calculate(s: String, variables: Hashtable<String?, Int?>): Int {
        val values = ArrayList<Int>()
        val operators = ArrayList<Char>()
        var i = 0
        while (i < s.length) {
            if (!is_space(s[i])) {
                if (s[i] == '(') {
                    operators.add('(')
                } else if (s[i] == ')') {
                    while (operators[operators.size - 1] != '(') {
                        process_op(values, operators[operators.size - 1])
                        operators.removeAt(operators.size - 1)
                    }
                    operators.removeAt(operators.size - 1)
                } else if (is_op(s[i])) {
                    val curop = s[i]
                    while (!operators.isEmpty() && priority(operators[operators.size - 1]) >= priority(
                            s[i]
                        )
                    ) {
                        process_op(values, operators[operators.size - 1])
                        operators.removeAt(operators.size - 1)
                    }
                    operators.add(curop)
                } else {
                    var operand = ""
                    while (i < s.length && Character.isLetterOrDigit(s[i])) {
                        operand += s[i++]
                    }
                    --i
                    if (Character.isDigit(operand[0])) {
                        values.add(operand.toInt())
                    } else {
                        values.add(variables[operand]!!)
                    }
                }
            }
            ++i
        }
        while (!operators.isEmpty()) {
            process_op(values, operators[operators.size - 1])
            operators.removeAt(operators.size - 1)
        }
        return values[values.size - 1]
    }

    /*@JvmStatic
    fun main(args: Array<String>) {
        val variables = Hashtable<String?, Int?>()
        val expr = "2*(a + b) - c/2 + 1" //10
        variables["a"] = 2
        variables["b"] = 3
        variables["c"] = 4
        println(calculate(expr, variables))
    }*/
}

