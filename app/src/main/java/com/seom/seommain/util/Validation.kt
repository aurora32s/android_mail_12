package com.seom.seommain.util

import android.util.Patterns
import com.seom.seommain.R

object Validation {
    /**
     * 닉네임 유효성 검사
     */
    fun checkValidateNickname(nickname: String): Int? {
        // 2. 영문과 숫자만 입력 가능
        if (!nickname.matches("^[0-9a-zA-Z]*\$".toRegex())) {
            return R.string.error_nickname_letter
        }
        // 1. 6글자 이상 16글자 이하만 가능
        if (nickname.length < 6 || nickname.length > 16) {
            return R.string.error_nickname_length
        }
        return null
    }

    /**
     * 이메일 유효성 검사
     */
    fun checkValidateEmail(email: String): Int? {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return R.string.error_email
        }
        return null
    }
}