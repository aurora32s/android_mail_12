package com.seom.seommain.model.mail

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.seom.seommain.R
import com.seom.seommain.model.Model

data class MailModel(
    val sender: String, // 송신인
    val title: String, // 메일 제목
    val content: String, // 메일 내용
    val date: String, // 메일 송신 날짜
    val type: MailType, // 메일 타입
    val profileImageType: ProfileImageType, // 프로필 이미지 타입
    val profileBackgroundColor: Int // 프로필 배경 색
): Model {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MailModel>() {
            override fun areItemsTheSame(oldItem: MailModel, newItem: MailModel) =
                oldItem.title === newItem.title

            override fun areContentsTheSame(oldItem: MailModel, newItem: MailModel) =
                oldItem == newItem
        }

        private val PROFILE_BACKGROUND_COLOR = listOf(
            R.color.profile_color_1,
            R.color.profile_color_2,
            R.color.profile_color_3,
            R.color.profile_color_4
        )
        fun getRandomBackground() = PROFILE_BACKGROUND_COLOR[(0..3).random()]
    }
}
