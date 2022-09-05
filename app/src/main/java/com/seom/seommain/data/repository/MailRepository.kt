package com.seom.seommain.data.repository

import com.seom.seommain.data.entity.MailEntity
import java.lang.Exception
import java.util.*

interface MailRepository {
    /**
     * 모든 메일 정보 요청
     */
    suspend fun getAllMail(): Result<List<MailEntity>>
}