package com.head.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.head.view.utils.ScreenUtil
import com.head.views.databinding.ActivityBilibiliBinding

class BilibiliActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = DataBindingUtil.setContentView<ActivityBilibiliBinding>(
            this,
            R.layout.activity_bilibili
        )
        contentView.headTitle01.getBuiltInTitle().setOnRightListener {
            ScreenUtil.hideSoftInputKeyBoard(it)
            finish()
        }.setOnSearchEditorActionListener {_, text ->
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }
}