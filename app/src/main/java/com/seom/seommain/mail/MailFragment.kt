package com.seom.seommain.mail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.seom.seommain.databinding.FragmentMailBinding

class MailFragment : Fragment() {

    private val viewModel = MailViewModel()
    private lateinit var binding: FragmentMailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
        // 메일 데이터 요청
        viewModel.fetchData()
    }

    private fun initViews() = with(binding) {
        titleTextView.text = "Seom Mail"
    }

    private fun bindViews() = with(binding) {

    }

    private fun observeData() = viewModel.mailStateLiveData.observe(viewLifecycleOwner) {
        when (it) {
            MailState.UnInitialized -> {
                initViews()
                bindViews()
            }
            MailState.Loading -> handleLoadingState()
            is MailState.Success -> handleSuccessState(it)
            MailState.Error -> handleErrorState()
        }
    }

    private fun handleLoadingState() {
        // TODO progress bar show
    }

    private fun handleSuccessState(state: MailState.Success) {
        // TODO recycler list 변경
        Log.d(TAG, state.mails.toString())
    }

    private fun handleErrorState() {
        // TODO Error 처리
    }

    companion object {
        const val TAG = "MailFragment"
    }
}