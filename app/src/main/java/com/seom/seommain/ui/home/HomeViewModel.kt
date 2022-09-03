package com.seom.seommain.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seom.seommain.ui.model.BaseType
import com.seom.seommain.ui.model.mail.MailType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _drawerSelectedType = MutableStateFlow<BaseType>(MailType.PRIMARY)
    val drawerSelectedType = _drawerSelectedType.asStateFlow()

    // 하단 bottom navigation 에서 선택한 tab
    private val _bottomSelectedTab = MutableLiveData<Int?>(null)
    val bottomSelectedTab get() = _bottomSelectedTab

    /**
     * drawer 에서 mail type 선택
     */
    fun changeDrawerSelectedType(type: BaseType) {
        _drawerSelectedType.value = type
    }

    /**
     * bottom tab 변경
     */
    fun changeBottomSelectedTab(tabId: Int) {
        _bottomSelectedTab.value = tabId
    }
}