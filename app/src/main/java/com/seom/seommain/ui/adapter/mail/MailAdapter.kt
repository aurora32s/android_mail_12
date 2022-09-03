package com.seom.seommain.ui.adapter.mail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.seom.seommain.databinding.ItemMailBinding
import com.seom.seommain.ui.model.mail.MailModel

class MailAdapter : ListAdapter<MailModel, MailViewHolder>(MailModel.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        return MailViewHolder(
            ItemMailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}