package jp.ac.it_college.std.s22020.calculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

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
            R.id.btnDot, R.id.btnPercent
        )
        for (v in btnList) findViewById<Button>(v).setOnClickListener(this)
    }

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

            R.id.btnDot -> sText += "."
            R.id.btnPercent -> sText += "%"
        }
        textView.text = sText
    }

}