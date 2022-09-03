package com.seom.seommain.ui.home.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.seom.seommain.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName = arguments?.getString(KEY_USER_NAME) ?: "UnKnown"
        val userEmail = arguments?.getString(KEY_USER_EMAIL) ?: "UnKnown"

    }

    companion object {
        const val TAG = "SettingFragment"
        const val KEY_USER_NAME = "user_name"
        const val KEY_USER_EMAIL = "user_email"

        fun newInstance(userName: String, userEmail: String): SettingFragment {
            val instance = SettingFragment()

            val bundle = Bundle()
            bundle.putString(KEY_USER_NAME, userName)
            bundle.putString(KEY_USER_EMAIL, userEmail)
            instance.arguments = bundle

            return instance
        }
    }
}