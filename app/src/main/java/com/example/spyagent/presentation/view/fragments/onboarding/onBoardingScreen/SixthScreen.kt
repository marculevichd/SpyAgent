package com.example.spyagent.presentation.view.fragments.onboarding.onBoardingScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spyagent.databinding.FragmentSixthScreenBinding
import com.example.spyagent.utils.NavHelper.navigateWithDeleteBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SixthScreen : Fragment() {

    private var _viewBinding: FragmentSixthScreenBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: SixthScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentSixthScreenBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewBinding.btnNextPage.setOnClickListener {
            viewModel.saveResultSawOnBoard()
            viewModel.navToNextFragment()
            viewModel.helpNav.observe(viewLifecycleOwner) {
                if(it!=null) {
                    navigateWithDeleteBackStack(it.destination, it.toDelete)
                    viewModel.userNavigated()
                }
            }
        }
    }


}