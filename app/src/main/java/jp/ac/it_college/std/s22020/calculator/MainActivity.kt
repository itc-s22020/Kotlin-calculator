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

        findViewById<Button>(R.id.btnZero).setOnClickListener(this)
        findViewById<Button>(R.id.btnOne).setOnClickListener(this)
        findViewById<Button>(R.id.btnTwo).setOnClickListener(this)
        findViewById<Button>(R.id.btnThree).setOnClickListener(this)
        findViewById<Button>(R.id.btnFour).setOnClickListener(this)
        findViewById<Button>(R.id.btnFive).setOnClickListener(this)
        findViewById<Button>(R.id.btnSix).setOnClickListener(this)
        findViewById<Button>(R.id.btnSeven).setOnClickListener(this)
        findViewById<Button>(R.id.btnEight).setOnClickListener(this)
        findViewById<Button>(R.id.btnNine).setOnClickListener(this)

        findViewById<Button>(R.id.btnBC).setOnClickListener(this)
        findViewById<Button>(R.id.btnAC).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val textView = findViewById<TextView>(R.id.textView)
        when (view.id) {
            R.id.btnZero -> sText += "0"
            R.id.btnOne  -> sText += "1"
            R.id.btnTwo  -> sText += "2"
            R.id.btnThree-> sText += "3"
            R.id.btnFour -> sText += "4"
            R.id.btnFive -> sText += "5"
            R.id.btnSix  -> sText += "6"
            R.id.btnSeven-> sText += "7"
            R.id.btnEight-> sText += "8"
            R.id.btnNine -> sText += "9"

            R.id.btnBC   -> sText = sText.dropLast(1)
            R.id.btnAC   -> sText = ""
            
        }
        textView.text = sText
    }

}