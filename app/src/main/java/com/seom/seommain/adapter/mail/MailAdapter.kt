package com.seom.seommain.adapter.mail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seom.seommain.databinding.ItemMailBinding
import com.seom.seommain.model.mail.MailModel

class MailAdapter(
    private val mailList: List<MailModel>
) : ListAdapter<MailModel, MailViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        return MailViewHolder(
            ItemMailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        holder.bind(mailList[position])
    }

    override fun getItemCount(): Int = mailList.size

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MailModel>() {
            override fun areItemsTheSame(oldItem: MailModel, newItem: MailModel): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: MailModel, newItem: MailModel): Boolean =
                oldItem.content == newItem.content
        }
    }
}