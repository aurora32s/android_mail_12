package com.seom.seommain.ui.adapter.mail

import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.seom.seommain.databinding.ItemMailBinding
import com.seom.seommain.ui.model.mail.MailModel
import com.seom.seommain.ui.model.mail.ProfileImageType

class MailViewHolder(
    private val binding: ItemMailBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(mail: MailModel) {
        binding.mail = mail

        val profileIsLetterType = mail.profileImageType == ProfileImageType.LETTER
        binding.imgProfile.isGone = profileIsLetterType
        binding.txtProfile.isVisible = profileIsLetterType

        if (profileIsLetterType) {
            binding.txtProfile.text = "${mail.sender.first()}"
            binding.txtProfile.backgroundTintList = ContextCompat.getColorStateList(
                binding.root.context,
                mail.profileBackgroundColor
            )
        }
    }
}