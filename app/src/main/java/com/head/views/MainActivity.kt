package com.head.views

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        binding.headTitleBar.getGeneralModeTitle()
            .centerSubText("-----------------------------------------------------------------------------------次标题--------------------------------------------------------------------次标题--------------------------------------------------------------------次标题---------------------------")
            .centerSubTextColor(Color.WHITE)
            .centerSubMarquee(true)
            .rightText("搜索").leftIcon(R.drawable.ic_baseline_arrow_back_ios_24)
            .centerMainText("-------------------主标题-------------------")
            .centerMainMarquee(true)
            .setOnLeftListener {
                Toast.makeText(this, "setOnLeftListener", Toast.LENGTH_SHORT).show()
            }.setOnCenterSubListener {
                Toast.makeText(this, "setOnCenterSubListener", Toast.LENGTH_SHORT).show()
            }.setOnCenterMainListener {
                Toast.makeText(this, "setOnCenterMainListener", Toast.LENGTH_SHORT).show()
            }
            .setOnRightListener {
                Toast.makeText(this, "setOnRightListener", Toast.LENGTH_SHORT).show()
            }.setOnRightSearchListener { v, text ->
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }.setOnSearchActionListener { v, text ->
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }.setOnSearchClearListener {
                Toast.makeText(this, "删除了", Toast.LENGTH_SHORT).show()

            }
        binding.headTitleBar.setHeadTitleBarTransparent()
        navView.setupWithNavController(navController)
    }


}

const val TAG = "HeadViews"