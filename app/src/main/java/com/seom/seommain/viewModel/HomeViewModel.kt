package com.seom.seommain.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seom.seommain.model.BaseType
import com.seom.seommain.model.mail.MailType

class HomeViewModel: ViewModel() {

    private val _drawerSelectedType = MutableLiveData<BaseType>(MailType.PRIMARY)
    val drawerSelectedType
        get() = _drawerSelectedType

    fun changeDrawerSelectedType(type: BaseType) {
        _drawerSelectedType.value = type
    }
}