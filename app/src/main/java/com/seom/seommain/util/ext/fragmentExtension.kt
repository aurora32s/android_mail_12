package com.seom.seommain.util.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

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

fun FragmentManager.pop(): String? {
    val name = this.getBackStackEntryAt(this.backStackEntryCount - 1).name
    this.popBackStack(name, 1)
    return name
}
