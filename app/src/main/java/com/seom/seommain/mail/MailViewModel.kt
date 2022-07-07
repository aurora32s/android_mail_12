package com.seom.seommain.mail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seom.seommain.model.mail.MailModel
import com.seom.seommain.data.repository.MailRepository
import com.seom.seommain.model.mail.MailType

class MailViewModel : ViewModel() {

    // TODO 의존성 주입으로 변경
    private val mailRepository = MailRepository()

    private val _mailStateLiveData = MutableLiveData<MailState>(MailState.UnInitialized)
    val mailStateLiveData
        get() = _mailStateLiveData

    private var mailList: List<MailModel> = emptyList()

    fun fetchData() {
        // mailList 초기화
        _mailStateLiveData.value = MailState.Loading
        val mails = mailRepository.getAllMail()

        mails?.let {
            mailList = mails.map { it.toModel() }
            changeMailType(MailType.PRIMARY)
        } ?: kotlin.run {
            _mailStateLiveData.value = MailState.Error
        }
    }

    fun changeMailType(mailType: MailType) {
        // mail type에 따른 리스트 반환
        _mailStateLiveData.value = MailState.Success(
            mailType.typeName,
            mailList.filter { it.type === mailType }
        )
    }
}