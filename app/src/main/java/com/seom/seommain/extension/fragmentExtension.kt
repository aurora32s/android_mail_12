package com.seom.seommain.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.push(fragment: Fragment, container: Int, tag: String) {
    this.beginTransaction()
        .replace(container, fragment)
        .addToBackStack(tag)
        .commit()
}

fun FragmentManager.replace(fragment: Fragment, container: Int) {
    this.beginTransaction()
        .replace(container, fragment)
        .commit()
}

fun FragmentManager.pop(): String? {
    val name = this.getBackStackEntryAt(this.backStackEntryCount - 1).name
    this.popBackStack(name, 1)
    return name
}
