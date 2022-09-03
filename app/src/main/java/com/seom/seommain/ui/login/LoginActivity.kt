package com.seom.seommain.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seom.seommain.ui.home.HomeActivity
import com.seom.seommain.databinding.ActivityLoginBinding
import com.seom.seommain.util.Validation
import com.seom.seommain.util.ext.setValidationCheckListener

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private var validateFlagForNickname = false // 닉네임 유효성 검사 flag
    private var validateFlagForEmail = false // 이메일 유효성 검사 flag

    /**
     * 첫 create 시에는 유효성 검사가 실행되지 않도록 처리
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        bindViews()
    }

    private fun initViews() = with(binding) {
        btnNext.isEnabled = false
    }

    private fun bindViews() = with(binding) {
        // nickname input
        etNickname.setValidationCheckListener(
            Validation::checkValidateNickname,
            { errorMsg ->
                txtNickname.error = errorMsg?.let { getString(errorMsg) }
                validateFlagForNickname = errorMsg == null
            },
            ::setEnabledNextBtn
        )
        // email
        etEmail.setValidationCheckListener(
            Validation::checkValidateEmail,
            { errorMsg ->
                txtEmail.error = errorMsg?.let { getString(errorMsg) }
                validateFlagForEmail = errorMsg == null
            },
            ::setEnabledNextBtn
        )
        // next button
        btnNext.setOnClickListener {
            // 홈 화면으로 이동
            val nickname = etNickname.text.toString()
            val email = etEmail.text.toString()
            startActivity(HomeActivity.getIntent(this@LoginActivity, nickname, email))
            /**
             * 백 버튼을 눌렀을 때는, 앱 종료
             */
            finish()
        }
    }

    private fun setEnabledNextBtn() = with(binding) {
        /**
         * 이메일과 닉네임의 유효성 검사가 모두 통과되어야만 '다음' 버튼 클릭 가능
         */
        btnNext.isEnabled = validateFlagForNickname && validateFlagForEmail
    }

    companion object {
        const val TAG = ".LoginActivity"
    }
}