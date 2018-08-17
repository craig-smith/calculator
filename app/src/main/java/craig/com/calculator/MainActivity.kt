package craig.com.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import craig.com.calculator.engine.EquationSolver
import craig.com.calculator.engine.Operator
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import java.util.Arrays.stream

class MainActivity : AppCompatActivity() {

    private var memory: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun number(view: View) {
        if (view is Button) {
            var numVal = view.text.toString()
            var curVal = input_text.text.toString()

            curVal = appendNumber(curVal, numVal)

            input_text.text = curVal
        }

    }

    private fun appendNumber(curVal: String, numVal: String): String {
        var curVal1 = curVal
        curVal1 += if (stream(Operator.values()).anyMatch({ operator -> curVal1.endsWith(operator.value) })) {
            " $numVal"
        } else {
            numVal
        }
        return curVal1
    }

    fun divide(view: View) {
        appendOperator(Operator.DIVIDE)
    }

    fun multiply(view: View) {
        appendOperator(Operator.MULTIPLY)
    }

    fun add(view: View) {
        appendOperator(Operator.ADD)
    }

    fun subtract(view: View) {
        appendOperator(Operator.SUBTRACT)
    }

    fun equals(view: View) {
        input_text.text = EquationSolver().solve(input_text.text.toString())
    }

    fun memoryClear(view: View) {
        memory = 0.0
    }

    fun memoryRecall(view: View) {
        val format = DecimalFormat()
        format.isDecimalSeparatorAlwaysShown = false
        var input = input_text.text.toString()
        input = appendNumber(input, format.format(memory))
        input_text.text = input
    }

    fun memoryAdd(view: View) {
        val currentEquation = EquationSolver().solve(input_text.text.toString()).toDouble()
        val memAdd = Operator.ADD.function.apply(currentEquation, memory)

        input_text.text = memAdd.toString()
    }

    fun memorySubtract(view: View) {
        val currentEquation = EquationSolver().solve(input_text.text.toString()).toDouble()
        val memSubtract = Operator.SUBTRACT.function.apply(currentEquation, memory)

        input_text.text = memSubtract.toString()
    }

    fun memoryStore(view: View) {
        memory = EquationSolver().solve(input_text.text.toString()).toDouble()
        input_text.text = ""
    }

    fun negate(view: View) {
        var input = EquationSolver().solve(input_text.text.toString()).toDouble()
        input = Operator.MULTIPLY.function.apply(input, -1.0)
        input_text.text = input.toString()
    }

    fun clearEntry(view: View) {
        var input = input_text.text.toString()
        val index = input.lastIndexOf(" ", 0, true)
        if (index > 0) {
            input_text.text = input.substring(0, index - 1)
        } else {
            input_text.text = ""
        }

    }

    fun clear(view: View) {
        input_text.text = ""
    }

    fun decimal(view: View) {
        var input = input_text.text.toString()
        val lastChar = input.last()
        if (lastChar.isDigit()) {
            input = "$input."
            input_text.text = input
        }

    }

    private fun appendOperator(operator: Operator) {
        var input = input_text.text.toString()
        input = "$input ${operator.value}"
        input_text.text = input
    }
}
