package com.seom.seommain.adapter.mail

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.seom.seommain.databinding.ItemMailBinding
import com.seom.seommain.model.mail.MailModel

class MailViewHolder(private val binding: ItemMailBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(mail: MailModel) = with(binding){
        Log.d("ViewHolder", mail.toString())
        senderTextView.text = mail.sender
        titleTextView.text = mail.title
        contentTextView.text = mail.content
        dateTextView.text = mail.date
    }
}