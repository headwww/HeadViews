package com.head.views.ui.button

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ButtonViewModel : ViewModel() {
    var check1:MutableLiveData<Boolean> = MutableLiveData(false)
    var check2:MutableLiveData<Boolean> = MutableLiveData(true)
    var progress1:MutableLiveData<Int> = MutableLiveData(0)
    var progress2:MutableLiveData<Int> = MutableLiveData(0)
    var progress3:MutableLiveData<Int> = MutableLiveData(0)
    var progress4:MutableLiveData<Int> = MutableLiveData(0)
    var progress5:MutableLiveData<Int> = MutableLiveData(0)
    var progress6:MutableLiveData<Int> = MutableLiveData(0)

}