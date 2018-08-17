package craig.com.calculator.engine

import java.util.Arrays.stream
import java.util.stream.Collectors
import java.util.stream.IntStream

/**
 * Created by Craig Smith on 8/12/2018.
 */
class EquationSolver {

    fun solve(equation: String): String {
        val equationParts = equation.split(" ").toMutableList()

        solveUsingOrderOfOperations(Operator.MULTIPLY, Operator.DIVIDE, equationParts)
        solveUsingOrderOfOperations(Operator.ADD, Operator.SUBTRACT, equationParts)

        return equationParts[0]
    }


    private fun solveUsingOrderOfOperations(operator1: Operator, operator2: Operator, equationParts: MutableList<String>) {
        val operatorIndices = IntStream.range(0, equationParts.size).filter({ i -> equationParts[i] == operator1.value }).mapToObj { i -> i }.collect(Collectors.toList())
        operatorIndices.addAll(IntStream.range(0, equationParts.size).filter({ i -> equationParts[i] == operator2.value }).mapToObj { i -> i }.collect(Collectors.toList()))
        operatorIndices.sort()

        var count = 0

        for (index in operatorIndices) {
            solveEquationPart(equationParts, index - count)
            count += 2
        }

    }

    private fun solveEquationPart(equationParts: MutableList<String>, index: Int) {
        val num1 = equationParts[index - 1].toDouble()
        val num2 = equationParts[index + 1].toDouble()
        val operator = stream(Operator.values()).filter({operator -> operator.value == equationParts[index]}).findFirst().get()
        equationParts[index] = operator.function.apply(num1, num2).toString()
        equationParts.removeAt(index - 1)
        equationParts.removeAt(index)
    }


}