package com.seom.seommain.model.mail

enum class MailType {
    PRIMARY,
    SOCIAL,
    PROMOTION;

    companion object {
        fun getType(type: Int): MailType? =
            when (type) {
                0 -> PRIMARY
                1 -> SOCIAL
                2 -> PROMOTION
                else -> null
            }
    }
}