package craig.com.calculator.engine

import java.util.function.BiFunction

/**
 * Created by polan on 8/11/2018.
 */
enum class Operator(val value: String, val function: BiFunction<Double, Double, Double>) {
    MULTIPLY("*", BiFunction { num1, num2 -> num1 * num2 }),
    DIVIDE("/", BiFunction { num1, num2 -> num1 / num2 }),
    SUBTRACT("-", BiFunction { num1, num2 -> num1 - num2 }),
    ADD("+", BiFunction { num1, num2 -> num1 + num2 });


}