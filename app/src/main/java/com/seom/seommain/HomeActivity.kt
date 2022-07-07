package com.seom.seommain

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.seom.seommain.databinding.ActivityHomeBinding
import com.seom.seommain.mail.MailFragment
import com.seom.seommain.setting.SettingFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    // fragments
    private val mailFragment = MailFragment()
    private val settingFragment = SettingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mailMenuItem -> replaceFragment(mailFragment, MailFragment.TAG)
                R.id.settingMenuItem -> replaceFragment(settingFragment, SettingFragment.TAG)
            }
            true
        }

        replaceFragment(mailFragment, MailFragment.TAG)
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
        const val USER_NAME = "UserName"
        const val USER_EMAIL = "UserEmail"

        fun getIntent(context: Context, nickname: String, email: String) =
            Intent(context, HomeActivity::class.java).apply {
                putExtra(USER_NAME, nickname)
                putExtra(USER_EMAIL, email)
            }
    }
}