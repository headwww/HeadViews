package com.head.views

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.head.view.HeadTitleBar
import com.head.view.menu.ItemView
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

        binding.lottieLayerName.playAnimation()

        binding.headBottom
            .addItem(
                ItemView(
                    checkedIcon = R.drawable.palyer_run,
                    unCheckedIcon =R.drawable.ic_baseline_visibility_24,
                    checkedLabelColor = Color.BLUE
                )
            )
            .addItem(
                ItemView(
                    checkedIcon = R.drawable.palyer_run,
                    unCheckedIcon = R.drawable.ic_baseline_visibility_off_24,
                    checkedLabel = "View"
                )
            )
            .addItem(
                ItemView(
                    checkedIcon = R.drawable.palyer_run,
                    unCheckedIcon = R.drawable.ic_baseline_visibility_off_24,
                    checkedLabel = "Home"
                )
            ).firstSelectedPosition(0)
            .setOnItemSelectedListener { v, position -> 
                Log.e("TAG", "onCreate: ${position}", )
             }
            .build()

        binding.headTitleBar.setHeadTitleBarTransparent()
        navView.setupWithNavController(navController)
    }

}
