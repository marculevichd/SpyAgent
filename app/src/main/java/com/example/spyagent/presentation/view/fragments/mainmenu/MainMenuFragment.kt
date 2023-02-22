package com.example.spyagent.presentation.view.fragments.mainmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.spyagent.databinding.FragmentMainMenuBinding
import com.example.spyagent.utils.NavHelper.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : Fragment() {

    private val viewModel: MainMenuViewModel by viewModels()

    private var _viewBinding: FragmentMainMenuBinding? = null
    private val viewBinding get() = _viewBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentMainMenuBinding.inflate(inflater)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.ruleImage.setOnClickListener {
            viewModel.navToRule()
            viewModel.helpNavRule.observe(viewLifecycleOwner) {
                if (it != null) {
                    navigate(it)
                    viewModel.userNavigatedToRule()
                }
            }
        }

        viewModel.addStartSet()


        viewBinding.btnSets.setOnClickListener {
            viewModel.navToSets()
            viewModel.helpNavSets.observe(viewLifecycleOwner) {
                if (it != null) {
                    navigate(it)
                    viewModel.userNavigatedToSets()
                }
            }
        }

        viewBinding.btnCreateGame.setOnClickListener {
            viewModel.navToCreateGame()
            viewModel.helpNavCreateGame.observe(viewLifecycleOwner) {
                if (it != null) {
                    navigate(it)
                    viewModel.userNavigatedToCreateGame()
                }
            }
        }

    }


}