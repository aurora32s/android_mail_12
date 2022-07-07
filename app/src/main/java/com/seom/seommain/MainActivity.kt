package com.seom.seommain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.seom.seommain.databinding.ActivityMainBinding
import com.seom.seommain.util.Validation

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var validateFlagForNickname = false // 닉네임 유효성 검사 flag
    private var validateFlagForEmail = false // 이메일 유효성 검사 flag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        bindViews()
    }

    private fun initViews() = with(binding) {
        nextButton.isEnabled = false
    }

    private fun bindViews() = with(binding) {
        // nickname input
        nicknameEditText.addTextChangedListener {
            // nickname 유효성 검사
            it?.let {
                Validation.checkValidateNickname(it.toString())?.let { errorMsg ->
                    nicknameTextInput.error = getString(errorMsg)
                    validateFlagForNickname = false
                } ?: kotlin.run {
                    nicknameTextInput.error = null
                    validateFlagForNickname = true
                }
                setEnabledNextBtn()
            }
        }
        // email
        emailEditText.addTextChangedListener {
            // email 유효성 검사
            it?.let {
                Validation.checkValidateEmail(it.toString())?.let { errorMsg ->
                    emailTextInput.error = getString(errorMsg)
                    validateFlagForEmail = false
                } ?: kotlin.run {
                    emailTextInput.error = null
                    validateFlagForEmail = true
                }
                setEnabledNextBtn()
            }
        }
        // next button
        nextButton.setOnClickListener {
            // TODO 메인 페이지로 이동
        }
    }

    private fun setEnabledNextBtn() = with(binding) {
        nextButton.isEnabled = validateFlagForNickname && validateFlagForEmail
    }
}