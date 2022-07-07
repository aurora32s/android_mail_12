package com.seom.seommain.data.entity

import com.seom.seommain.extension.format
import com.seom.seommain.model.mail.MailModel
import com.seom.seommain.model.mail.MailType
import java.util.*

data class MailEntity(
    val id: Int,
    val sender: String,
    val title: String,
    val content: String,
    val date: Date,
    val type: Int // 메일 타입
) {
    fun toModel() = MailModel(
        sender = sender,
        title = title,
        content = content,
        date = date.format(),
        type = MailType.getType(type) ?: MailType.PRIMARY
    )
}