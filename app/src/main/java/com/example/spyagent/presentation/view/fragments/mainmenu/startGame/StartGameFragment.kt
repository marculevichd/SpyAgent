package com.example.spyagent.presentation.view.fragments.mainmenu.startGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spyagent.R
import com.example.spyagent.databinding.FragmentStartGameBinding
import com.example.spyagent.utils.NavHelper.navigateWithBundleAndDeleteBackStack
import com.example.spyagent.utils.NavHelper.navigateWithDeleteBackStack
import com.example.spyagent.utils.Const.NUMBER_OF_PLAYER_VALUE
import com.example.spyagent.utils.Const.NUMBER_OF_SPIES_VALUE
import com.example.spyagent.utils.Const.SHOW_CATEGORY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartGameFragment : Fragment() {

    private val viewModel: StartGameViewModel by viewModels()

    private var _viewBinding: FragmentStartGameBinding? = null
    private val viewBinding get() = _viewBinding!!

    private var playerTurn: Int = 1
    private var showWord: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentStartGameBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments

        bundle?.let {

            viewModel.doGamePreparation(
                bundle.getInt(NUMBER_OF_PLAYER_VALUE),
                bundle.getInt(NUMBER_OF_SPIES_VALUE)
            )

            if (bundle.getBoolean(SHOW_CATEGORY)) {
                viewModel.gamePreparation.observe(viewLifecycleOwner) {
                    viewBinding.set.text = it.gameSetModel.setName
                }
            }

            viewBinding.cardViewPlayer.setOnClickListener {
                showWord = !showWord

                viewModel.gamePreparation.observe(viewLifecycleOwner) {
                    if (showWord) {
                        if (playerTurn != it.spy) {
                                viewBinding.playerAndWord.text = it.word
                                viewBinding.description.text = getString(R.string.descriptionWord)
                            playerTurn++
                        } else {
                            viewBinding.playerAndWord.text = getString(R.string.you_are_spy)
                            viewBinding.description.text = getString(R.string.descriptionSpy)
                            playerTurn++
                        }
                    } else {
                        if (it.amountPlayers < playerTurn) {
                            viewModel.navToTimer()
                            viewModel.navHelperTimer.observe(viewLifecycleOwner) {
                                if (it != null) {
                                    navigateWithBundleAndDeleteBackStack(
                                        it.destination,
                                        bundle,
                                        it.fragmentToDelete
                                    )
                                    viewModel.userNavigatedToTimer()
                                }
                            }
                        }
                        viewBinding.playerAndWord.text = getString(R.string.player) + " $playerTurn"
                        viewBinding.description.text = getString(R.string.descriptionPlayer)
                    }
                }
            }
        }

        viewBinding.back.setOnClickListener {
            viewModel.userNavToMainMenu()
            viewModel.helpNavUserToMainMenu.observe(viewLifecycleOwner) {
                if (it != null) {
                    navigateWithDeleteBackStack(it.destination, it.fragmentToDelete)
                    viewModel.userNavigatedToMainMenu()
                }
            }
        }


    }
}