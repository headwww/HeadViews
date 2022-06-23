package com.head.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.head.view.utils.ScreenUtil
import com.head.views.databinding.ActivityBilibiliBinding
import com.head.views.ui.edittext.EditTextViewViewModel

class BilibiliActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = DataBindingUtil.setContentView<ActivityBilibiliBinding>(
            this,
            R.layout.activity_bilibili
        )
        var viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(application)).get(EditTextViewViewModel::class.java)
        contentView.viewmodel=viewModel
        contentView.lifecycleOwner = this
        contentView.headTitle01.getBuiltInTitle().setOnRightListener {
            ScreenUtil.hideSoftInputKeyBoard(it)
            finish()
        }.setOnSearchEditorActionListener {_, text ->
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }
}