package com.seom.seommain.ui.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigationrail.NavigationRailView
import com.seom.seommain.R
import com.seom.seommain.databinding.ActivityHomeBinding
import com.seom.seommain.databinding.DrawerHeaderBinding
import com.seom.seommain.ui.home.mail.MailFragment
import com.seom.seommain.ui.home.setting.SettingFragment
import com.seom.seommain.ui.model.mail.MailType
import com.seom.seommain.util.extension.replace

class HomeActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initObserve()
        bindViews()
    }

    private fun initViews() = with(binding) {
        setSupportActionBar(toolbar)

        // drawer 기본 setting
        root.setActionBarDrawerToggle(this@HomeActivity, toolbar)

        // navigation drawer header setting
        DrawerHeaderBinding.bind(
            navigationView.getHeaderView(0)
        ).run {
            val nickname = intent.getStringExtra(USER_NAME) ?: "익명님"
            val email = intent.getStringExtra(USER_EMAIL) ?: "없음"

            nicknameTextView.text = nickname
            emailTextView.text = email
        }

        viewModel.changeBottomSelectedTab(R.id.mailMenuItem)
    }

    private fun bindViews() = with(binding) {
        // bottom tab 변경
        when (bottomNavigation) {
            is BottomNavigationView -> {
                bottomNavigation.setOnItemSelectedListener(this@HomeActivity)
            }
            is NavigationRailView -> {
                bottomNavigation.setOnItemSelectedListener(this@HomeActivity)
            }
        }

        // navigation drawer 에서 mail 타입 변경
        navigationView.setNavigationItemSelectedListener {
            viewModel.changeDrawerSelectedType(
                when (it.itemId) {
                    R.id.primaryMailType -> MailType.PRIMARY
                    R.id.socialMailType -> MailType.SOCIAL
                    R.id.promotionMailType -> MailType.PROMOTION
                    else -> MailType.PRIMARY
                }
            )
            root.closeDrawer(navigationView)
            false
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        viewModel.changeBottomSelectedTab(item.itemId)
        return true
    }

    private fun initObserve() {
        // bottom tab item observe
        viewModel.bottomSelectedTab.observe(this@HomeActivity) {
            when (it) {
                R.id.mailMenuItem -> { // mail tab
                    supportFragmentManager.replace(
                        R.id.fragmentContainer,
                        MailFragment.newInstance(),
                        MailFragment.TAG
                    )
                    binding.toolbar.isVisible = true
                }
                R.id.settingMenuItem -> { // setting tab
                    supportFragmentManager.replace(
                        R.id.fragmentContainer,
                        SettingFragment.newInstance(),
                        SettingFragment.TAG
                    )
                    binding.toolbar.isGone = true
                }
            }
        }
    }

    companion object {
        const val TAG = ".HomeActivity"
        const val USER_NAME = "UserName"
        const val USER_EMAIL = "UserEmail"

        fun getIntent(context: Context, nickname: String, email: String) =
            Intent(context, HomeActivity::class.java).apply {
                putExtra(USER_NAME, nickname) // 사용자 닉네임
                putExtra(USER_EMAIL, email) // 사용자 이메일
            }
    }
}