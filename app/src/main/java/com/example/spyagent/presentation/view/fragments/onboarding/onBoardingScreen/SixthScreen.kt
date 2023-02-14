package com.example.spyagent.presentation.view.fragments.onboarding.onBoardingScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.spyagent.R
import com.example.spyagent.databinding.FragmentSixthScreenBinding

class SixthScreen : Fragment() {

    private var _viewBinding: FragmentSixthScreenBinding? = null
    private val viewBinding get() = _viewBinding!!

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


        }
    }


}