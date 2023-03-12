package com.example.spyagent.presentation.view.fragments.mainmenu.rule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spyagent.databinding.FragmentWebRuleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebRuleFragment : Fragment() {

    private val viewModel: WebRuleViewModel by viewModels()

    private var _viewBinding: FragmentWebRuleBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentWebRuleBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.webViewRule.webViewClient = WebViewClient()
        viewBinding.webViewRule.settings.javaScriptEnabled = true

        viewModel.getUrlToWebRule()
        viewModel.helpUrl.observe(viewLifecycleOwner){
            viewBinding.webViewRule.loadUrl(it)
        }
    }
}