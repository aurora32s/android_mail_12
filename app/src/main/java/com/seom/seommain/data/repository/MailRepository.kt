package com.seom.seommain.data.repository

import com.seom.seommain.data.entity.MailEntity
import java.lang.Exception
import java.util.*

class MailRepository {

    fun getAllMail(): List<MailEntity>? {
        try {
            val mockData = (0..100).map {
                MailEntity(
                    id = it,
                    sender = "${it}th sender",
                    title = "title $it",
                    content = "content $it",
                    date = Date(),
                    type = it % 3
                )
            }

            return mockData.toList()
        } catch (exception: Exception) {
            return null
        }
    }
}