package com.seom.seommain.ui.home.mail

import com.seom.seommain.ui.model.mail.MailModel

sealed interface MailUiState {
    object UnInitialized : MailUiState
    object Loading : MailUiState
    object Success : MailUiState
    object Error : MailUiState
}