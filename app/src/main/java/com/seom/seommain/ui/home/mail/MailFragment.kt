package com.seom.seommain.ui.home.mail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.seommain.ui.home.HomeActivity
import com.seom.seommain.ui.adapter.mail.MailAdapter
import com.seom.seommain.databinding.FragmentMailBinding
import com.seom.seommain.ui.model.mail.MailType
import com.seom.seommain.ui.home.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MailFragment : Fragment() {

    // TODO 의존성 주입 도입할 때 코드 수정하기
    private val viewModel = MailViewModel()
    private val sharedViewModel by lazy {
        ViewModelProvider(activity as HomeActivity).get(
            HomeViewModel::class.java
        )
    }

    private lateinit var binding: FragmentMailBinding
    private val mailAdapter by lazy { MailAdapter() }

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
        initObserve()
    }

    private fun initViews() = with(binding) {
        mailRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this@MailFragment.context,
                LinearLayoutManager.VERTICAL
            )
        )
        mailRecyclerView.adapter = mailAdapter
    }

    private fun initObserve() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    // type 에 맞게 filtering 된 mail 리스트
                    viewModel.mails.collect {
                        mailAdapter.submitList(it)
                    }
                }
                launch {
                    // ui state 변경
                    viewModel.mailUiState.collect {
                        when (it) {
                            MailUiState.UnInitialized -> viewModel.fetchData()
                            else -> {}
                        }
                    }
                }
                launch {
                    sharedViewModel.drawerSelectedType.collect {
                        (it as? MailType)?.let { type ->
                            binding.mailTypeTextView.text = (type.typeName)
                            viewModel.changeMailType(type)
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "MailFragment"
        fun newInstance() = MailFragment()
    }
}