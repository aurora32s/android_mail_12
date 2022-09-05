package com.seom.seommain.data.repository.impl

import com.seom.seommain.data.entity.MailEntity
import com.seom.seommain.data.repository.MailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class MailRepositoryImpl @Inject constructor() : MailRepository {
    override suspend fun getAllMail(): Result<List<MailEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val mockData = (0..100).map {
                    MailEntity(
                        id = it,
                        sender = if (it % 2 == 0) "${it}th sender" else "sender ${it}th",
                        title = "title $it",
                        content = "content $it",
                        date = Date(),
                        type = it % 3
                    )
                }

                Result.success(mockData.toList())
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }
}