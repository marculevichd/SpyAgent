package com.example.spyagent.presentation.view.fragments.mainmenu.createGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spyagent.R
import com.example.spyagent.databinding.FragmentCreateGameBinding
import com.example.spyagent.utils.NavHelper.navigate
import com.example.spyagent.utils.NavHelper.navigateWithBundleAndDeleteBackStack
import com.example.spyagent.utils.Const
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
            val stringList = it.joinToString(" ")
            viewBinding.createGameSelectCategoriesSelectedCategories.text = stringList
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

        viewBinding.createGameTimerPlusOne.setOnClickListener {
            if (Integer.parseInt(viewBinding.createGameTimerValue.text.toString()) < 10) {
                viewModel.timerPlusOne(Integer.parseInt(viewBinding.createGameTimerValue.text.toString()))
                viewModel.timerValue.observe(viewLifecycleOwner) {
                    viewBinding.createGameTimerValue.text = it.toString()
                }
            }
        }

        viewBinding.createGameTimerMinusOne.setOnClickListener {
            if (Integer.parseInt(viewBinding.createGameTimerValue.text.toString()) > 1) {
                viewModel.timerMinusOne(Integer.parseInt(viewBinding.createGameTimerValue.text.toString()))
                viewModel.timerValue.observe(viewLifecycleOwner) {
                    viewBinding.createGameTimerValue.text = it.toString()
                }
            }
        }

        viewBinding.confirmButton.setOnClickListener {
            viewModel.checkDoesGameSetExist()

            viewModel.doesGameSetExist.observe(viewLifecycleOwner) {
                if (it) {
                    viewModel.navToStartGame(
                        Integer.parseInt(viewBinding.createGameNumberOfPlayerValue.text.toString()),
                        Integer.parseInt(viewBinding.createGameNumberOfSpiesValue.text.toString()),
                        Integer.parseInt(viewBinding.createGameTimerValue.text.toString()),
                        viewBinding.createGameCategoriesCheckBox.isChecked
                    )
                    viewModel.helpNavToSet.observe(viewLifecycleOwner) {
                        if (it != null) {
                            val bundle = Bundle()
                            bundle.putInt(Const.NUMBER_OF_PLAYER_VALUE, it.numberOfPlayerValue)
                            bundle.putInt(Const.NUMBER_OF_SPIES_VALUE, it.numberOfSpiesValue)
                            bundle.putInt(Const.TIMER_VALUE, it.timerValue)
                            bundle.putBoolean(Const.SHOW_CATEGORY, it.showCategory)

                            navigateWithBundleAndDeleteBackStack(
                                it.destination,
                                bundle,
                                it.fragmentToDelete
                            )
                            viewModel.userNavigatedToStartGame()
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.add_no_less_then_one_set),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }


    }


}