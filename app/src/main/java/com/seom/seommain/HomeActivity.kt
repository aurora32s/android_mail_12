package com.seom.seommain

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.seom.seommain.databinding.ActivityHomeBinding
import com.seom.seommain.databinding.DrawerHeaderBinding
import com.seom.seommain.databinding.HomeBodyVerticalBinding
import com.seom.seommain.extension.pop
import com.seom.seommain.extension.push
import com.seom.seommain.extension.replace
import com.seom.seommain.mail.MailFragment
import com.seom.seommain.model.mail.MailType
import com.seom.seommain.setting.SettingFragment
import com.seom.seommain.viewModel.HomeViewModel

class HomeActivity : AppCompatActivity() {

    //viewModel
    private val viewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }

    private lateinit var binding: ActivityHomeBinding

    // drawer
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObserve()
        initViews()
        bindViews()
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
            true
        }

        navigationView.setNavigationItemSelectedListener {
            viewModel.changeDrawerSelectedType(
                when (it.itemId) {
                    R.id.primaryMailType -> MailType.PRIMARY
                    R.id.socialMailType -> MailType.SOCIAL
                    R.id.promotionMailType -> MailType.PROMOTION
                    else -> MailType.PRIMARY
                }
            )
            binding.root.closeDrawer(binding.navigationView)
            false
        }
    }

    private fun initObserve() {
        // bottom tab item observe
        viewModel.bottomSelectedTab.observe(this@HomeActivity) {
            when (it) {
                R.id.mailMenuItem -> { // mail tab
                    // TODO mail 탭으로 변경
                }
                R.id.settingMenuItem -> { // setting tab
                    // TODO setting 탭으로 변경
                }
            }
        }
    }

    override fun onBackPressed() {
        // 1. setting tab에서 back button 클릭 시에는 mail tab으로 이동
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.pop()?.let {
                val navigationId = it.toInt()
                binding.bottomNavigation.selectedItemId = navigationId
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