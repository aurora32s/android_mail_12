package com.seom.seommain

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.seom.seommain.databinding.ActivityHomeBinding
import com.seom.seommain.databinding.DrawerHeaderBinding
import com.seom.seommain.mail.MailFragment
import com.seom.seommain.model.mail.MailType
import com.seom.seommain.setting.SettingFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    // fragments
    private val mailFragment = MailFragment()
    private val settingFragment = SettingFragment()

    // drawer
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private val _mailTypeLiveData = MutableLiveData<MailType>(MailType.PRIMARY)
    val mailTypeLiveData
        get() = _mailTypeLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        bindViews()

        replaceFragment(mailFragment, MailFragment.TAG)
    }

    private fun showAppBar() {
        binding.toolbar.isVisible = true
    }

    private fun hideAppBar() {
        binding.toolbar.isGone = true
    }

    private fun initViews() = with(binding) {
        setSupportActionBar(toolbar)

        drawerToggle = ActionBarDrawerToggle(
            this@HomeActivity,
            root,
            toolbar,
            R.string.app_name,
            R.string.app_name
        )
        root.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        val nickname = intent.getStringExtra(USER_NAME) ?: "익명님"
        val email = intent.getStringExtra(USER_EMAIL) ?: "없음"

        // navigation header data binding
        val headerView = navigationView.getHeaderView(0)
        val headerBinding = DrawerHeaderBinding.bind(headerView)

        headerBinding.nicknameTextView.text = nickname
        headerBinding.emailTextView.text = email
    }

    private fun bindViews() = with(binding) {
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mailMenuItem -> {
                    replaceFragment(mailFragment, MailFragment.TAG)
                    showAppBar()
                }
                R.id.settingMenuItem -> {
                    replaceFragment(settingFragment, SettingFragment.TAG)
                    hideAppBar()
                }
            }
            true
        }

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.primaryMailType -> _mailTypeLiveData.value = MailType.PRIMARY
                R.id.socialMailType -> _mailTypeLiveData.value = MailType.SOCIAL
                R.id.promotionMailType -> _mailTypeLiveData.value = MailType.PROMOTION
                else -> {}
            }

            binding.root.closeDrawer(binding.navigationView)
            false
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)

        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction().hide(fm).commitAllowingStateLoss()
        }

        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, fragment, tag)
                commit()
            }
        }
    }

    companion object {
        const val TAG = ".HomeActivity"
        const val USER_NAME = "UserName"
        const val USER_EMAIL = "UserEmail"

        fun getIntent(context: Context, nickname: String, email: String) =
            Intent(context, HomeActivity::class.java).apply {
                putExtra(USER_NAME, nickname)
                putExtra(USER_EMAIL, email)
            }
    }
}