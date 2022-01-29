package by.bsuir.mobile.cat

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout


class MainActivity : AppCompatActivity() {

    private lateinit var heartAnimation: Animation
    private lateinit var catAnimation: Animation
    private lateinit var button: Button
    private lateinit var score: EditText
    private lateinit var satietyText: TextView
    private lateinit var catImage: ImageView
    private lateinit var heartImage: ImageView


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)





//        heartAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_anim)
//        catAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
//
//        button = findViewById(R.id.button)
//        score = findViewById(R.id.score)
//        satietyText = findViewById(R.id.satiety_text);
//        catImage = findViewById(R.id.catView)
//        heartImage = findViewById(R.id.heartView)
//        initHeartAnimationListener()
//
//        button.setOnClickListener {
//            onButtonPressed()
//        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    private fun onButtonPressed() {
        val updatedScoreValue = (score.text.toString().toInt() + 1)
        if (updatedScoreValue % 100 == 0) {
            catImage.startAnimation(catAnimation)
            heartImage.startAnimation(heartAnimation)
        }
        score.setText("" + updatedScoreValue.toString())

    }

    private fun initHeartAnimationListener() {
        heartAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {
                heartImage.alpha = 1F
            }

            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                heartImage.alpha = 0F
            }
        })
    }

}