package jp.ac.it_college.std.s22020.calculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


var sText = ""

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.LTGRAY

        val btnList = listOf(
            R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine,
            R.id.btnBC, R.id.btnAC,
            R.id.btnPlus, R.id.btnMinus, R.id.btnTimes, R.id.btnDivided,
            R.id.btnTwoZero, R.id.btnPercent,
            R.id.btnBrackets, R.id.btnEqual
        )
        for (v in btnList) findViewById<Button>(v).setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View) {
        val textView = findViewById<TextView>(R.id.textView)
        when (view.id) {
            R.id.btnZero -> sText += "0"
            R.id.btnOne -> sText += "1"
            R.id.btnTwo -> sText += "2"
            R.id.btnThree -> sText += "3"
            R.id.btnFour -> sText += "4"
            R.id.btnFive -> sText += "5"
            R.id.btnSix -> sText += "6"
            R.id.btnSeven -> sText += "7"
            R.id.btnEight -> sText += "8"
            R.id.btnNine -> sText += "9"

            R.id.btnBC -> sText = sText.dropLast(1)
            R.id.btnAC -> sText = ""

            R.id.btnPlus -> sText += "+"
            R.id.btnMinus -> sText += "-"
            R.id.btnTimes -> sText += "×"
            R.id.btnDivided -> sText += "÷"

            R.id.btnTwoZero -> sText += "00"
            R.id.btnPercent -> sText += "%"

            R.id.btnBrackets -> { sText +=
                    if (sText.isEmpty()) { "(" }
                    else if (sText.last() == '(') { "(" }
                    else if ( sText.count{x -> x == '('} > sText.count{y -> y == ')'}) { ")" }
                    else { "(" }
            }

            R.id.btnEqual -> {
                sText = try {
                    Parser(sText).expr().toString()
                } catch (e: NumberFormatException) {
                    "Error"
                }
            }

        }
        if (sText.take(5) == "Error" && sText.length > 5) {
            sText = sText.drop(5)
            textView.text = sText
        } else {
            textView.text = sText
        }
    }

}

internal open class Source(private val str: String) {
    private var pos = 0
    fun peek(): Int {
        return if (pos < str.length) {
            str[pos].code
        } else -1
    }

    operator fun next() {
        ++pos
    }
}

internal class Parser(str: String) : Source(str) {
    private fun number(): Int {
        val sb = StringBuilder()
        var ch: Int
        while (peek().also { ch = it } >= 0 && Character.isDigit(ch)) {
            sb.append(ch.toChar())
            next()
        }
        return sb.toString().toInt()
    }

    // expr = term, {("+", term) | ("-", term)}
    fun expr(): Int {
        var x = term()
        while (true) {
            when (peek()) {
                '+'.code -> {
                    next()
                    x += term()
                    continue
                }

                '-'.code -> {
                    next()
                    x -= term()
                    continue
                }
            }
            break
        }
        return x
    }

    // term = factor, {("*", factor) | ("/", factor)}
    private fun term(): Int {
        var x = factor()
        while (true) {
            when (peek()) {
                '×'.code -> {
                    next()
                    x *= factor()
                    continue
                }

                '÷'.code -> {
                    next()
                    x /= factor()
                    continue
                }
            }
            break
        }
        return x
    }

    // factor = factor = [spaces], ("(", expr, ")") | number, [spaces]
    private fun factor(): Int {
        val ret: Int
        spaces()
        if (peek() == '('.code) {
            next()
            ret = expr()
            if (peek() == ')'.code) {
                next()
            }
        } else {
            ret = number()
        }
        spaces()
        return ret
    }

    private fun spaces() {
        while (peek() == ' '.code) {
            next()
        }
    }
}


