package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    // Declare the UI elements
    private lateinit var expressionText: TextView
    private lateinit var resultText: TextView
    private lateinit var acButton: Button
    private lateinit var deleteButton: Button
    private lateinit var equalButton: Button
    private lateinit var zeroButton: Button
    private lateinit var zeroZeroButton: Button
    private lateinit var oneButton: Button
    private lateinit var twoButton: Button
    private lateinit var threeButton: Button
    private lateinit var fourButton: Button
    private lateinit var fiveButton: Button
    private lateinit var sixButton: Button
    private lateinit var sevenButton: Button
    private lateinit var eightButton: Button
    private lateinit var nineButton: Button
    private lateinit var plusButton: Button
    private lateinit var minusButton: Button
    private lateinit var timesButton: Button
    private lateinit var slashButton: Button
    private lateinit var dotButton: Button
    private lateinit var restButton: Button

    // For storing the current expression
    private var expression = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpResources()

        acButton.setOnClickListener {
            // Clear expression and result
            expression.clear()
            expressionText.text = ""
            resultText.text = "0"
        }

        deleteButton.setOnClickListener {
            // Remove the last character from the expression
            if (expression.isNotEmpty()) {
                expression.deleteCharAt(expression.length - 1)
                expressionText.text = expression.toString()
            }
        }

        equalButton.setOnClickListener {
            // Evaluate the expression when equal button is clicked
            if (expression.isNotEmpty()) {
                try {
                    val exp = ExpressionBuilder(expression.toString()).build()
                    val result = exp.evaluate()

                    // Check if the result is a whole number
                    if (result == result.toInt().toDouble()) {
                        resultText.text = result.toInt().toString() // Display as integer
                    } else {
                        resultText.text = result.toString() // Display as float
                    }

                    // Reset the expression after showing result
                    expression.clear()
                    expressionText.text = "" // Clear the expression display

                } catch (e: Exception) {
                    resultText.text = "Error" // Handle evaluation errors
                }
            }
        }

        // Number button listeners
        zeroButton.setOnClickListener { appendToExpression("0") }
        zeroZeroButton.setOnClickListener { appendToExpression("00") } // Handle 00 button
        oneButton.setOnClickListener { appendToExpression("1") }
        twoButton.setOnClickListener { appendToExpression("2") }
        threeButton.setOnClickListener { appendToExpression("3") }
        fourButton.setOnClickListener { appendToExpression("4") }
        fiveButton.setOnClickListener { appendToExpression("5") }
        sixButton.setOnClickListener { appendToExpression("6") }
        sevenButton.setOnClickListener { appendToExpression("7") }
        eightButton.setOnClickListener { appendToExpression("8") }
        nineButton.setOnClickListener { appendToExpression("9") }

        // Operator button listeners
        plusButton.setOnClickListener { appendToExpression("+") }
        minusButton.setOnClickListener { appendToExpression("-") }
        timesButton.setOnClickListener { appendToExpression("*") }
        slashButton.setOnClickListener { appendToExpression("/") }
        dotButton.setOnClickListener { appendToExpression(".") }
        restButton.setOnClickListener { appendToExpression("%") } // Handle percent button
    }

    private fun setUpResources() {
        expressionText = findViewById(R.id.expression)
        resultText = findViewById(R.id.result)
        acButton = findViewById(R.id.ac_button)
        deleteButton = findViewById(R.id.c_button)
        equalButton = findViewById(R.id.equal_button)
        zeroButton = findViewById(R.id.zero_button)
        zeroZeroButton = findViewById(R.id.zerozero_button) // Initialize 00 button
        oneButton = findViewById(R.id.one_button)
        twoButton = findViewById(R.id.two_button)
        threeButton = findViewById(R.id.three_button)
        fourButton = findViewById(R.id.four_button)
        fiveButton = findViewById(R.id.five_button)
        sixButton = findViewById(R.id.six_button)
        sevenButton = findViewById(R.id.seven_button)
        eightButton = findViewById(R.id.eight_button)
        nineButton = findViewById(R.id.nine_button)
        plusButton = findViewById(R.id.plus_button)
        minusButton = findViewById(R.id.minus_button)
        timesButton = findViewById(R.id.times_button)
        slashButton = findViewById(R.id.slash_button)
        dotButton = findViewById(R.id.dot_button)
        restButton = findViewById(R.id.rest_button) // Initialize percent button
    }

    private fun appendToExpression(value: String) {
        // Prevent adding multiple operators or dots consecutively
        if (value == "." && expression.endsWith(".")) return

        // Check if the last character is an operator
        if ("+-*/%".contains(value) && expression.isNotEmpty() &&
            "+-*/%".contains(expression.last())) {
            // Replace the last operator with the new operator
            expression.deleteCharAt(expression.length - 1)
        } else if ("+-*/%".contains(value) && expression.isEmpty()) {
            // Prevent adding operator if expression is empty
            return
        }

        // Append the value to the expression
        expression.append(value)
        expressionText.text = expression.toString() // Update the expression display
    }
}
