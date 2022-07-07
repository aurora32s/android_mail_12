package com.seom.seommain.adapter.mail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seom.seommain.databinding.ItemMailBinding
import com.seom.seommain.model.mail.MailModel

class MailAdapter() : ListAdapter<MailModel, MailViewHolder>(MailModel.DIFF_CALLBACK) {

    override fun getItemViewType(position: Int): Int = currentList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        return MailViewHolder(
            ItemMailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}