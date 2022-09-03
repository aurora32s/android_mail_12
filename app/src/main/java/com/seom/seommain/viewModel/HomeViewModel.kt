package com.seom.seommain.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seom.seommain.model.BaseType
import com.seom.seommain.model.mail.MailType

class HomeViewModel : ViewModel() {

    private val _drawerSelectedType = MutableLiveData<BaseType>(MailType.PRIMARY)
    val drawerSelectedType get() = _drawerSelectedType

    // 하단 bottom navigation 에서 선택한 tab
    private val _bottomSelectedTab = MutableLiveData<Int?>(null)
    val bottomSelectedTab get() = _bottomSelectedTab

    /**
     * drawer 에서 mail type 선택
     */
    fun changeDrawerSelectedType(type: BaseType) {
        _drawerSelectedType.value = type
    }
}