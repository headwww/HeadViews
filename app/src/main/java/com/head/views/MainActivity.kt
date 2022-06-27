package com.head.views

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.head.view.HeadTitleBar
import com.head.view.NavigationItemView
import com.head.views.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        var theme = true
        binding.headTitleBar.getBuiltInTitle().setOnRightListener {
            Toast.makeText(this, "切换", Toast.LENGTH_SHORT).show()
            if (theme) {
                theme = false
                binding.headTitleBar.setHeadTitleBarTheme(HeadTitleBar.Theme.DARK)
            } else {
                theme = true
                binding.headTitleBar.setHeadTitleBarTheme(HeadTitleBar.Theme.LIGHT)
            }
        }

        binding.headBottom
            .addItem(NavigationItemView(this))
            .addItem(NavigationItemView(this))
            .addItem(NavigationItemView(this))
            .build()
        binding.headTitleBar.setHeadTitleBarTransparent()
        navView.setupWithNavController(navController)
    }

}

const val TAG = "HeadViews"