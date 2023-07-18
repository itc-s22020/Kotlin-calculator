package jp.ac.it_college.std.s22020.calculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

var sText = ""
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = Color.LTGRAY


        findViewById<Button>(R.id.btnOne).setOnClickListener(this)

    }


    override fun onClick(view: View) {
        var textView = findViewById<TextView>(R.id.textView)
        when (view.id) {
            R.id.btnOne -> {
                sText += "1"
                textView.text = sText
            }
        }
    }

}