package by.bsuir.mobile.cat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_layout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        println("hello")
    }
}