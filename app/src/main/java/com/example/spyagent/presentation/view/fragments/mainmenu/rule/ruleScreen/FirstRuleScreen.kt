package com.example.spyagent.presentation.view.fragments.mainmenu.rule.ruleScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.spyagent.R
import com.example.spyagent.databinding.FragmentFirstRuleScreenBinding

class FirstRuleScreen : Fragment() {

    private var _viewBinding: FragmentFirstRuleScreenBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentFirstRuleScreenBinding.inflate(inflater)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        viewBinding.btnNextPage.setOnClickListener {
            viewPager?.currentItem = 1
        }

    }

}