package com.seom.seommain.model.mail

import android.util.Log
import androidx.recyclerview.widget.DiffUtil

data class MailModel(
    val sender: String, // 송신인
    val title: String, // 메일 제목
    val content: String, // 메일 내용
    val date: String, // 메일 송신 날짜
    val type: MailType // 메일 타입
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MailModel>() {
            override fun areItemsTheSame(oldItem: MailModel, newItem: MailModel) =
                oldItem.title === newItem.title

            override fun areContentsTheSame(oldItem: MailModel, newItem: MailModel) =
                oldItem == newItem
        }
    }
}
