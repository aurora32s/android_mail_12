package com.seom.seommain.ui.home.mail

import com.seom.seommain.ui.model.mail.MailModel

sealed interface MailState {

    object UnInitialized: MailState
    object Loading: MailState

    data class Success(
        val mailType: String,
        val mails: List<MailModel>
    ): MailState

    object Error: MailState
}