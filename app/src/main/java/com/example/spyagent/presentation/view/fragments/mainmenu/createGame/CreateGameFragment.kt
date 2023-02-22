package com.example.spyagent.presentation.view.fragments.mainmenu.createGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spyagent.databinding.FragmentCreateGameBinding
import com.example.spyagent.utils.NavHelper.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateGameFragment : Fragment() {

    private val viewModel: CreateGameViewModel by viewModels()

    private var _viewBinding: FragmentCreateGameBinding? = null
    private val viewBinding get() = _viewBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentCreateGameBinding.inflate(inflater)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewBinding.createGameSelectCategoriesNavToChoose.setOnClickListener {
            viewModel.navToChooseCategory()
            viewModel.helpNavChooseCategory.observe(viewLifecycleOwner) {
                if (it != null) {
                    navigate(it)
                    viewModel.userNavigatedToChooseCategory()
                }
            }
        }

        viewModel.showSetsWhichSelected()
        viewModel.listSetsWhichSelected.observe(viewLifecycleOwner) {
            for (i in it) {
                viewBinding.createGameSelectCategoriesSelectedCategories.text =
                    "${viewBinding.createGameSelectCategoriesSelectedCategories.text} $i "
            }
        }

        viewBinding.createGameNumberOfPlayerPlusOne.setOnClickListener {
            if (Integer.parseInt(viewBinding.createGameNumberOfPlayerValue.text.toString()) < 10) {
                viewModel.numberOfPlayersPlusOne(Integer.parseInt(viewBinding.createGameNumberOfPlayerValue.text.toString()))
                viewModel.numberOfPlayers.observe(viewLifecycleOwner) {
                    viewBinding.createGameNumberOfPlayerValue.text = it.toString()
                }
            }
        }

        viewBinding.createGameNumberOfPlayerMinusOne.setOnClickListener {
            if (Integer.parseInt(viewBinding.createGameNumberOfPlayerValue.text.toString()) > 3) {
                viewModel.numberOfPlayersMinusOne(Integer.parseInt(viewBinding.createGameNumberOfPlayerValue.text.toString()))
                viewModel.numberOfPlayers.observe(viewLifecycleOwner) {
                    viewBinding.createGameNumberOfPlayerValue.text = it.toString()
                }
            }
        }


        viewBinding.createGameNumberOfSpiesPlusOne.setOnClickListener {
            if (Integer.parseInt(viewBinding.createGameNumberOfSpiesValue.text.toString()) < 3) {
                viewModel.numberOfSpiesPlusOne(Integer.parseInt(viewBinding.createGameNumberOfSpiesValue.text.toString()))
                viewModel.numberOfSpies.observe(viewLifecycleOwner) {
                    viewBinding.createGameNumberOfSpiesValue.text = it.toString()
                }
            }
        }

        viewBinding.createGameNumberOfSpiesMinusOne.setOnClickListener {
            if (Integer.parseInt(viewBinding.createGameNumberOfSpiesValue.text.toString()) > 1) {
                viewModel.numberOfSpiesMinusOne(Integer.parseInt(viewBinding.createGameNumberOfSpiesValue.text.toString()))
                viewModel.numberOfSpies.observe(viewLifecycleOwner) {
                    viewBinding.createGameNumberOfSpiesValue.text = it.toString()
                }
            }
        }


    }


}