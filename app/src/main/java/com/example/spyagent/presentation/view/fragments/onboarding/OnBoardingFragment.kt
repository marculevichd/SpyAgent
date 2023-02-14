package com.example.spyagent.presentation.view.fragments.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spyagent.databinding.FragmentOnBoardingBinding
import com.example.spyagent.presentation.view.fragments.onboarding.onBoardingScreen.*
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingFragment : Fragment() {

    private var _viewBinding: FragmentOnBoardingBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentOnBoardingBinding.inflate(inflater)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen(),
            FourthScreen(),
            FifthScreen(),
            SixthScreen()
        )
        val adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        viewBinding.viewPager.adapter = adapter


        TabLayoutMediator(viewBinding.tabLayout, viewBinding.viewPager) { _, _ ->
        }.attach()


    }

}