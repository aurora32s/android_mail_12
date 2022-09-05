package com.seom.seommain.ui.home.mail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seom.seommain.ui.model.mail.MailModel
import com.seom.seommain.data.repository.MailRepository
import com.seom.seommain.ui.model.mail.MailType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
    private val mailRepository: MailRepository
) : ViewModel() {
    private val _mailUiState = MutableStateFlow<MailUiState>(MailUiState.UnInitialized)
    val mailUiState = _mailUiState.asStateFlow()

    private val mailType = MutableStateFlow(MailType.PRIMARY)
    private val _mails = MutableStateFlow<List<MailModel>>(emptyList())
    val mails = _mails.asStateFlow().combine(mailType) { mails, mailType ->
        mails.filter { it.type == mailType }
    }

    fun fetchData() {
        viewModelScope.launch {
            mailRepository.getAllMail()
                .onSuccess {
                    _mails.value = it.map { mail -> mail.toModel() }
                    _mailUiState.value = MailUiState.Success
                }
                .onFailure {
                    _mailUiState.value = MailUiState.Error
                }
        }
    }

    fun changeMailType(nextMailType: MailType) {
        // 사용자에 의해 mailType 이 변경된 경우
        mailType.value = nextMailType
    }
}