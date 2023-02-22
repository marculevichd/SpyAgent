package com.example.spyagent.presentation.view.fragments.mainmenu.createGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spyagent.databinding.FragmentChooseCategoryBinding
import com.example.spyagent.domain.model.SetModel
import com.example.spyagent.presentation.view.fragments.mainmenu.createGame.adapter.ChooseCategoryAdapter
import com.example.spyagent.presentation.view.fragments.mainmenu.createGame.adapter.ChooseCategoryListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseCategoryFragment : Fragment(), ChooseCategoryListener {

    private var _viewBinding: FragmentChooseCategoryBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: ChooseCategoryViewModel by viewModels()

    lateinit var chooseCategoryAdapter: ChooseCategoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentChooseCategoryBinding.inflate(inflater)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseCategoryAdapter = ChooseCategoryAdapter(this)

        viewBinding.recyclerViewChooseCategory.layoutManager = LinearLayoutManager(context)
        viewBinding.recyclerViewChooseCategory.adapter = chooseCategoryAdapter

        viewModel.showSets()
        viewModel.listSets.observe(viewLifecycleOwner) {
            chooseCategoryAdapter.submitList(it)
        }


    }

    override fun setSelected(setModel: SetModel, isSelected:Boolean) {
        if (isSelected) {
            viewModel.addSetToGameDataBase(setModel)
        } else {
            viewModel.deleteFromSetToGameDataBase(setModel.id)
        }

    }


}