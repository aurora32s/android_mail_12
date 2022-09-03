package com.seom.seommain.ui.model.mail

import com.seom.seommain.ui.model.BaseType

enum class MailType(val typeName: String): BaseType {
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