package com.head.views.ui.edittext

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.head.view.HeadEditTextView

class EditTextViewViewModel : ViewModel() {
    var progress1:MutableLiveData<Int> = MutableLiveData(0)
    var progress2:MutableLiveData<Int> = MutableLiveData(0)
    var progress3:MutableLiveData<Int> = MutableLiveData(0)
    var progress4:MutableLiveData<Int> = MutableLiveData(0)
    var check1:MutableLiveData<Boolean> = MutableLiveData(false)
    var check2:MutableLiveData<Boolean> = MutableLiveData(false)
    var check3:MutableLiveData<Boolean> = MutableLiveData(false)
    fun leftClick(v: HeadEditTextView){
        Log.e("===","leftClick")
    }
    fun rightClick(v: HeadEditTextView){
        Log.e("===","rightClick")
    }

}