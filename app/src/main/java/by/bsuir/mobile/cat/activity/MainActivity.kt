package by.bsuir.mobile.cat.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import by.bsuir.mobile.cat.R
import by.bsuir.mobile.cat.databinding.ActivityMainBinding
import by.bsuir.mobile.cat.model.CatStatistic
import by.bsuir.mobile.cat.service.DatabaseManager
import java.time.LocalDateTime


class MainActivity : AppCompatActivity() {
    private val databaseManager = DatabaseManager(this)

    private lateinit var binding: ActivityMainBinding
    private lateinit var heartAnimation: Animation
    private lateinit var catAnimation: Animation
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        heartAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_anim)
        catAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)

        initHeartAnimationListener()

        binding.button.setOnClickListener {
            onButtonPressed()
        }

        initNavigationViewListener()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDestroy() {
        databaseManager.openDb()
        databaseManager.insert(
            CatStatistic(
                time = LocalDateTime.now().toString(),
                satiety = binding.score.text.toString().toInt()
            )
        )
        databaseManager.close()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return true
    }


    private fun initNavigationViewListener() {
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.about_menu -> {
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                }
                else -> println("default")
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share_id) {
            val intent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(
                    Intent.EXTRA_TEXT,
                    "My new record is: " + binding.score.text.toString()
                )
                this.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                this.type = "text/plain"
            }
            startActivity(Intent.createChooser(intent, "Share record"))
        }

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    private fun onButtonPressed() {
        val updatedScoreValue = (binding.score.text.toString().toInt() + 1)
        if (updatedScoreValue % 100 == 0) {
            binding.catView.startAnimation(catAnimation)
            binding.heartView.startAnimation(heartAnimation)
        }
        binding.score.setText("" + updatedScoreValue.toString())
    }

    private fun initHeartAnimationListener() {
        heartAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {
                binding.heartView.alpha = 1F
            }

            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                binding.heartView.alpha = 0F
            }
        })
    }

}