package com.seom.seommain.mail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.seommain.adapter.mail.MailAdapter
import com.seom.seommain.databinding.FragmentMailBinding

class MailFragment : Fragment() {

    private val viewModel = MailViewModel()
    private lateinit var binding: FragmentMailBinding

    private val mailAdapter by lazy {
        MailAdapter()
    }

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

        initViews()
        bindViews()
        observeData()
    }

    private fun initViews() = with(binding) {
        titleTextView.text = "Seom Mail"

        mailRecyclerView.layoutManager = LinearLayoutManager(this@MailFragment.context)
        mailRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this@MailFragment.context,
                LinearLayoutManager.VERTICAL
            )
        )
        mailRecyclerView.adapter = mailAdapter
    }

    private fun bindViews() = with(binding) {

    }

    private fun observeData() = viewModel.mailStateLiveData.observe(viewLifecycleOwner) {
        Log.d(TAG, it.toString())
        when (it) {
            MailState.UnInitialized -> {
                // 메일 데이터 요청
                viewModel.fetchData()
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

        binding.mailTypeTextView.text = "\uD83D\uDC49\uD83C\uDFFB ${state.mailType}"
        // recycler list 변경
        mailAdapter.submitList(state.mails)
    }

    private fun handleErrorState() {
        // TODO Error 처리
    }

    companion object {
        const val TAG = "MailFragment"
    }
}