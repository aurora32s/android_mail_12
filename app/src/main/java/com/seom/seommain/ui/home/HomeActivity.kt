package com.seom.seommain.ui.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import com.seom.seommain.R
import com.seom.seommain.databinding.ActivityHomeBinding
import com.seom.seommain.databinding.DrawerHeaderBinding
import com.seom.seommain.util.extension.pop
import com.seom.seommain.ui.model.mail.MailType

class HomeActivity : AppCompatActivity() {

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
    }

    private fun bindViews() = with(binding) {
        // bottom tab 변경
//        bottomNavigation.setOnItemSelectedListener {setOnItemSelectedListener
//            viewModel.changeBottomSelectedTab(it.itemId)
//            /**
//             * bottom sheet 의 icon 변경 동작까지 수행하려면 true 반환
//             */
//            true
//        }

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