package com.seom.seommain.ui.home.mail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.seommain.ui.home.HomeActivity
import com.seom.seommain.ui.adapter.mail.MailAdapter
import com.seom.seommain.databinding.FragmentMailBinding
import com.seom.seommain.ui.model.mail.MailType
import com.seom.seommain.ui.home.HomeViewModel

class MailFragment : Fragment() {

    private val viewModel = MailViewModel()
    private val sharedViewModel by lazy {
        ViewModelProvider(activity as HomeActivity).get(
            HomeViewModel::class.java
        )
    }
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
    }

    override fun onResume() {
        super.onResume()
        // 메일 데이터 요청
        viewModel.fetchData()
        observeMailTypeData()
        observeData()
    }

    private fun initViews() = with(binding) {
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

    private fun observeMailTypeData() {
        sharedViewModel.drawerSelectedType.observe(activity as HomeActivity) {
            viewModel.changeMailType(it as MailType)
        }
    }

    private fun handleLoadingState() {
        // TODO progress bar show
    }

    private fun handleSuccessState(state: MailState.Success) {

        Log.d(TAG, "handle success")
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