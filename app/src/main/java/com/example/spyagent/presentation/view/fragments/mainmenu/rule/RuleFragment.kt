package com.example.spyagent.presentation.view.fragments.mainmenu.rule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spyagent.R
import com.example.spyagent.databinding.FragmentMainMenuBinding
import com.example.spyagent.databinding.FragmentRuleBinding
import com.example.spyagent.presentation.view.fragments.mainmenu.rule.ruleScreen.*
import com.example.spyagent.presentation.view.fragments.onboarding.ViewPagerAdapter
import com.example.spyagent.presentation.view.fragments.onboarding.onBoardingScreen.*
import com.example.spyagent.utils.NavHelper.navigate
import com.google.android.material.tabs.TabLayoutMediator

class RuleFragment : Fragment() {


    private var _viewBinding: FragmentRuleBinding? = null
    private val viewBinding get() = _viewBinding!!

    private  val viewModel: RuleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentRuleBinding.inflate(inflater)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            FirstRuleScreen(),
            SecondRuleScreen(),
            ThirdRuleScreen(),
            FourthRuleScreen(),
            FifthRuleScreen(),
            SixthRuleScreen(),
            SeventhRuleScreen(),
            EighthRuleScreen()
        )
        val adapter =
            ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        viewBinding.viewPager.adapter = adapter


//        TabLayoutMediator(viewBinding.tabLayout, viewBinding.viewPager) { _, _ ->
//        }.attach()





        viewBinding.openScrollView.setOnClickListener {
            navigate(R.id.action_ruleFragment_to_webRuleFragment)
        }


    }


}