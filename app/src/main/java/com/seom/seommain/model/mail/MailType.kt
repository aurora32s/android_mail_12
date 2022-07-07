package com.seom.seommain.model.mail

enum class MailType(val typeName: String) {
    PRIMARY("primary"),
    SOCIAL("social"),
    PROMOTION("promotion");

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