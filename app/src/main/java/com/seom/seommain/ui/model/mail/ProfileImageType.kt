package com.seom.seommain.ui.model.mail

enum class ProfileImageType {
    LETTER,
    IMAGE;

    companion object {
        fun getProfileImageType(sender: String): ProfileImageType =
            if (sender.matches(Regex("^[a-zA-Z].*\$"))) {
                // 영문으로 시작하는 경우
                LETTER
            } else { // 그 외
                IMAGE
            }
    }
}