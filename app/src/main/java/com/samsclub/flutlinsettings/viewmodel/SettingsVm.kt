package com.samsclub.flutlinsettings.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by y0c021m on 5/25/18.
 */
class SettingsVm : ViewModel() {
    val toggleData: MutableLiveData<Boolean> = MutableLiveData()
    val editableText: MutableLiveData<String> = MutableLiveData()
    val editableText2: MutableLiveData<String> = MutableLiveData()
}