package com.example.androidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
//import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {

    lateinit var myTextViewInput: TextView

    var lastNumberic = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myTextViewInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        myTextViewInput.append((view as Button).text)
        lastNumberic = true
    }

    fun onClear(view: View) {
        myTextViewInput.text = null
        lastNumberic = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        if (lastNumberic && !lastDot) {
            myTextViewInput.append(".")
            lastNumberic = false
            lastDot = true
        }
    }

    fun onEqual(view: View) {
        if (lastNumberic) {
            var tvValue = myTextViewInput.text.toString()
            var prefix = ""

            try {
                if(tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    myTextViewInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    myTextViewInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    myTextViewInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    myTextViewInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result

        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value

    }

    fun onOperator(view: View) {
        if (lastNumberic && !isOperationAdded(myTextViewInput.text.toString())) {
            myTextViewInput.append((view as Button).text)
            lastNumberic = false
            lastDot = true
        }
    }

    private fun isOperationAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            return false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

}