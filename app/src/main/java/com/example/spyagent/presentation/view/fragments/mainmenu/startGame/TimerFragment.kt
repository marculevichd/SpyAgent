package com.example.spyagent.presentation.view.fragments.mainmenu.startGame

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spyagent.R
import com.example.spyagent.databinding.FragmentTimerBinding
import com.example.spyagent.utils.Const.TIMER_VALUE
import com.example.spyagent.utils.NavHelper.navigateWithDeleteBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimerFragment : Fragment() {

    private var _viewBinding: FragmentTimerBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: TimerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentTimerBinding.inflate(inflater)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle = arguments
        bundle?.let {
            val timerValue = (bundle.getInt(TIMER_VALUE) * 60 * 1_000).toLong()

            viewBinding.chronometer.isCountDown = true
            viewBinding.chronometer.base = SystemClock.elapsedRealtime() + timerValue

            viewBinding.startChronometer.setOnClickListener {
                viewBinding.chronometer.base = SystemClock.elapsedRealtime() + timerValue
                viewBinding.chronometer.start()
                viewBinding.startChronometer.isClickable = false
            }
        }

        viewBinding.chronometer.setOnChronometerTickListener {
            if (viewBinding.chronometer.base <= SystemClock.elapsedRealtime()) {
                viewBinding.chronometer.stop()
                viewBinding.startAsking.text = getString(R.string.vote)
                viewBinding.startChronometer.text = getString(R.string.play_again)
                viewBinding.startChronometer.isClickable = true

                viewBinding.startChronometer.setOnClickListener {
                    viewModel.navToMainMenu()
                    viewModel.helpNavToMainMenu.observe(viewLifecycleOwner) {
                        if (it != null) {
                            navigateWithDeleteBackStack(it.destination, it.deleteFromBackStack)
                            viewModel.userNavigatedToStartGame()
                        }
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