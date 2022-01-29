package by.bsuir.mobile.cat

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.marginStart
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var score: EditText
    private lateinit var satietyText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        score = findViewById(R.id.score)
        satietyText = findViewById(R.id.satiety_text);

        button.setOnClickListener {
            onButtonPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onButtonPressed() {
        val updatedScoreValue = (score.text.toString().toInt() + 1)
        score.setText("" + updatedScoreValue.toString())
    }

}