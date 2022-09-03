package com.seom.seommain.ui.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.seom.seommain.R

class MailDrawerContainer(
    context: Context,
    attrs: AttributeSet
) : DrawerLayout(context, attrs) {

    fun setActionBarDrawerToggle(activity: Activity, toolbar: Toolbar) {
        val drawerToggle = ActionBarDrawerToggle(
            activity,
            this,
            toolbar,
            R.string.app_name,
            R.string.app_name
        )

        this.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }
}