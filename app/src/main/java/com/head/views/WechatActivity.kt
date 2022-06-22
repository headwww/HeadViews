package com.head.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.head.view.utils.ScreenUtil
import com.head.views.databinding.ActivityWechatBinding

class WechatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView =
            DataBindingUtil.setContentView<ActivityWechatBinding>(this, R.layout.activity_wechat)
        contentView.cancel.getBuiltInTitle().setOnRightListener {
            ScreenUtil.hideSoftInputKeyBoard(it)
            finish()
        }.setOnSearchEditorActionListener {_, text ->
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }
}