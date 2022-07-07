package com.seom.seommain.model.mail

data class MailModel(
    val sender: String, // 송신인
    val title: String, // 메일 제목
    val content: String, // 메일 내용
    val date: String, // 메일 송신 날짜
    val type: MailType // 메일 타입
)
