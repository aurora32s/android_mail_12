package com.seom.seommain.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seom.seommain.ui.model.BaseType
import com.seom.seommain.ui.model.mail.MailType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    // mail 타입
    private val _drawerSelectedType = MutableStateFlow<BaseType>(MailType.PRIMARY)
    val drawerSelectedType = _drawerSelectedType.asStateFlow()

    private val _stackForMailType = mutableListOf<BaseType>()
    val stackForMailType: List<BaseType>
        get() = _stackForMailType

    // 하단 bottom navigation 에서 선택한 tab
    private val _bottomSelectedTab = MutableLiveData<Int?>(null)
    val bottomSelectedTab get() = _bottomSelectedTab

    /**
     * drawer 에서 mail type 선택
     */
    fun changeDrawerSelectedType(type: BaseType) {
        _stackForMailType.add(_drawerSelectedType.value)
        _drawerSelectedType.value = type
    }

    /**
     * bottom tab 변경
     */
    fun changeBottomSelectedTab(tabId: Int) {
        // 동일한 tab 을 클릭한 경우에는 무시
        if (_bottomSelectedTab.value == tabId) return
        _bottomSelectedTab.value = tabId
    }

    fun popMailType() {
        _drawerSelectedType.value = _stackForMailType.removeLast()
    }
}