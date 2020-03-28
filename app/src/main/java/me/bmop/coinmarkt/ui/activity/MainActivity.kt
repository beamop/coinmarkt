package me.bmop.coinmarkt.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.ui.adapter.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    private var isOpen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabShowMenu.setOnClickListener {
            setupFabMenu()
        }

        fabShowNews.setOnClickListener {
            startActivity(Intent(this, NewsActivity::class.java))
        }

        setupViewPager()
    }

    private fun setupViewPager() {
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    private fun setupFabMenu() {
        val fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        val fabRClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
        val fabAClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise)

        isOpen = if (isOpen) {
            fabShowNews.startAnimation(fabClose)
            fabShowMenu.startAnimation(fabRClockwise)

            fabShowNews.isClickable = false

            false
        } else {
            fabShowNews.startAnimation(fabOpen)
            fabShowMenu.startAnimation(fabAClockwise)

            fabShowNews.isClickable = true

            true
        }
    }

}

fun Context.toast(message: CharSequence, length: Int) =
    Toast.makeText(this, message, length).show()