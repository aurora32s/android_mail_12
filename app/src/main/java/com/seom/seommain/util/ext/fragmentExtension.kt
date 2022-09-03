package com.seom.seommain.util.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.seom.seommain.ui.home.mail.MailFragment

fun FragmentManager.replace(
    containerId: Int,
    fragment: Fragment,
    fragmentTag: String? = null
) {
    this.commit {
        setReorderingAllowed(true)
        addToBackStack(fragmentTag)
        replace(containerId, fragment, fragmentTag)
    }
}

fun FragmentManager.toMailTab(
    containerId: Int
) {
    // 첫 home 화면 진입 시
    if (this.backStackEntryCount == 0) {
        this.commit {
            setReorderingAllowed(true)
            add(containerId, MailFragment.newInstance(), MailFragment.TAG)
        }
    } else {
        // setting tab 에서 mail tab 으로 이동
        this.popBackStack()
    }
}

fun FragmentManager.pop(): String? {
    val name = this.getBackStackEntryAt(this.backStackEntryCount - 1).name
    this.popBackStack(name, 1)
    return name
}
