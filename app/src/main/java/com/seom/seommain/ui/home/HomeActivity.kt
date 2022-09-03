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
import com.seom.seommain.ui.home.setting.SettingFragment
import com.seom.seommain.ui.model.mail.MailType
import com.seom.seommain.util.extension.replace
import com.seom.seommain.util.extension.toMailTab

class HomeActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(USER_NAME) ?: "익명님"
        val email = intent.getStringExtra(USER_EMAIL) ?: "없음"

        initViews(name, email)
        initObserve(name, email)
        bindViews()
    }

    private fun initViews(name: String, email: String) = with(binding) {
        setSupportActionBar(toolbar)

        // drawer 기본 setting
        root.setActionBarDrawerToggle(this@HomeActivity, toolbar)

        // navigation drawer header setting
        DrawerHeaderBinding.bind(
            navigationView.getHeaderView(0)
        ).run {
            nicknameTextView.text = name
            emailTextView.text = email
        }

        viewModel.changeBottomSelectedTab(R.id.mailMenuItem)
    }

    private fun bindViews() = with(binding) {
        // bottom tab 변경
        (bottomNavigation as? NavigationBarView)?.setOnItemSelectedListener(this@HomeActivity)

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

    private fun initObserve(name: String, email: String) {
        // bottom tab item observe
        viewModel.bottomSelectedTab.observe(this@HomeActivity) {
            when (it) {
                R.id.mailMenuItem -> { // mail tab
                    supportFragmentManager.toMailTab(R.id.fragmentContainer)
                    binding.toolbar.isVisible = true
                }
                R.id.settingMenuItem -> { // setting tab
                    supportFragmentManager.replace(
                        R.id.fragmentContainer,
                        SettingFragment.newInstance(name, email),
                        SettingFragment.TAG
                    )
                    binding.toolbar.isGone = true
                }
            }
            it?.let { setNavigationItem(it) }
        }
    }

    private fun setNavigationItem(itemId: Int) = with(binding) {
        // bottom tab 변경
        (bottomNavigation as? NavigationBarView)?.selectedItemId = itemId
    }

    override fun onBackPressed() {
        // 1. mail tab 에서 back button 클릭
        if (supportFragmentManager.backStackEntryCount == 0) {
            // 선택한 mail type 이 없는 경우
            if (viewModel.stackForMailType.isEmpty()) {
                super.onBackPressed()
            } else {
                viewModel.popMailType()
            }
        } else { // 2. setting tab 에서 back button 클릭
            viewModel.changeBottomSelectedTab(R.id.mailMenuItem)
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