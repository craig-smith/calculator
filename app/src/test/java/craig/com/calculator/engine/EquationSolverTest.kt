package craig.com.calculator.engine

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test

/**
* Created by Craig Smith on 8/13/2018.
*/
class EquationSolverTest {

    private val solver = EquationSolver()

    @Test
    fun shouldAddTwoNums() {
        val equation = "2 + 2"
        val result = solver.solve(equation)

        assertThat(result).isEqualTo("4.0")
    }

    @Test
    fun shouldSubtractTwoNums() {
        val equation = "2 - 1"
        val result = solver.solve(equation)

        assertThat(result).isEqualTo("1.0")
    }

    @Test
    fun shouldMultiplyTwoNums() {
        val equation = "2 * 2"
        val result = solver.solve(equation)

        assertThat(result).isEqualTo("4.0")
    }

    @Test
    fun shouldDivideTwoNums() {
        val equation = "2 / 2"
        val result = solver.solve(equation)

        assertThat(result).isEqualTo("1.0")
    }

    @Test
    fun shouldMultiplyBeforeAdd() {
        val equation = "2 + 2 * 2"
        val result = solver.solve(equation)

        assertThat(result).isEqualTo("6.0")
    }

    @Test
    fun shouldDivideBeforeAdd() {
        val equation = "2 + 2 / 2"
        val result = solver.solve(equation)

        assertThat(result).isEqualTo("3.0")
    }

    @Test
    fun shouldMultiplyBeforeSubtract() {
        val equation = "2 - 2 * 2"
        val result = solver.solve(equation)

        assertThat(result).isEqualTo("-2.0")
    }

    @Test
    fun shouldDivideBeforeSubtract() {
        val equation = "2 - 2 / 2"
        val result = solver.solve(equation)

        assertThat(result).isEqualTo("1.0")
    }

    @Test
    fun shouldSolveMoreComplexEquation() {
        val equation = "2 - 3 * 2 + 1"
        val result = solver.solve(equation)

        assertThat(result).isEqualTo("-3.0")
    }

    @Test
    fun shouldFollowLeftToRightOrderOfOperationWhenConflictingOperatorsAreFound() {
        val equation = "2 / 2 * 2"
        val result = solver.solve(equation)

        assertThat(result).isEqualTo("2.0")
    }

    @Test
    fun shouldSolveEquationWithFourOperators() {
        val equation = "2 + 5 * 2 + 3"
        val result = solver.solve(equation)

        assertThat(result).isEqualTo("15.0")
    }

    @Test
    fun shouldSolveEquationWithFiveParts() {
        val equation = "2 + 6 / 3 - 2 * 6"
        val result = solver.solve(equation)

        assertThat(result).isEqualTo("-8.0")
    }

}