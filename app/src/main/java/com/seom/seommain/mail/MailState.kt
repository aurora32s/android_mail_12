package com.seom.seommain.mail

import com.seom.seommain.model.mail.MailModel

sealed interface MailState {

    object UnInitialized: MailState
    object Loading: MailState

    data class Success(
        val mails: List<MailModel>
    ): MailState

    object Error: MailState
}