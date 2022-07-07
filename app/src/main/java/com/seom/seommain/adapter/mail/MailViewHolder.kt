package com.seom.seommain.adapter.mail

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.seom.seommain.R
import com.seom.seommain.databinding.ItemMailBinding
import com.seom.seommain.model.mail.MailModel
import com.seom.seommain.model.mail.ProfileImageType

class MailViewHolder(private val binding: ItemMailBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(mail: MailModel) = with(binding) {
        senderTextView.text = mail.sender
        titleTextView.text = mail.title
        contentTextView.text = mail.content
        dateTextView.text = mail.date

        // profile image 설정
        if (mail.profileImageType === ProfileImageType.LETTER) {
            // sender 이름이 영어로 시작하는 경우
            profileimageView.isGone = true
            profileTextView.isVisible = true

            profileTextView.text = mail.sender.first().toString()
            senderProfileImage.backgroundTintList = ContextCompat.getColorStateList(
                binding.root.context,
                mail.profileBackgroundColor
            );
        } else {
            profileimageView.isVisible = true
            profileTextView.isGone = true
        }
    }
}