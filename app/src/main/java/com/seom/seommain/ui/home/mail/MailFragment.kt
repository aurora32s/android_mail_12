package com.seom.seommain.ui.home.mail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MailFragment : Fragment() {

    private val viewModel: MailViewModel by viewModels()
    private val sharedViewModel: HomeViewModel by activityViewModels()

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
                    // type ??? ?????? filtering ??? mail ?????????
                    viewModel.mails.collect {
                        mailAdapter.submitList(it)
                    }
                }
                launch {
                    // ui state ??????
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