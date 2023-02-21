package com.example.spyagent.presentation.view.fragments.mainmenu.sets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spyagent.databinding.FragmentSetsBinding
import com.example.spyagent.domain.model.SetModel
import com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter.SetsAdapter
import com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter.SetsListener
import com.example.spyagent.utils.Const.ID_BUNDLE_SETS
import com.example.spyagent.utils.Const.SET_NAME_BUNDLE_SETS
import com.example.spyagent.utils.NavHelper.navigate
import com.example.spyagent.utils.NavHelper.navigateWithBundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetsFragment : Fragment(), SetsListener {

    private val viewModel: SetsViewModel by viewModels()

    private var _viewBinding: FragmentSetsBinding? = null
    private val viewBinding get() = _viewBinding!!

    lateinit var setsAdapter: SetsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentSetsBinding.inflate(inflater)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setsAdapter = SetsAdapter(this)

        viewBinding.recyclerViewFriends.layoutManager = LinearLayoutManager(context)
        viewBinding.recyclerViewFriends.adapter = setsAdapter


        viewModel.showSets()
        viewModel.listSets.observe(viewLifecycleOwner) {
            setsAdapter.submitList(it)
        }

        viewBinding.floatingActionButton.setOnClickListener {
            viewModel.openAddNewSet()
            viewModel.helpNavToAddNewSet.observe(viewLifecycleOwner) {
                if (it != null) {
                    navigate(it)
                    viewModel.userNavigatedToAddNewSet()
                }
            }
        }

        viewBinding.setsSearch.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(query: String?): Boolean {
                viewModel.searchSets("%$query%")
                return true
            }
        })
        viewModel.foundSets.observe(viewLifecycleOwner) {
            setsAdapter.submitList(it)
        }
    }




    override fun onOpenSetClicked(setModel: SetModel) {
        viewModel.navToSet(setModel)
        viewModel.helpNavToSet.observe(viewLifecycleOwner) {
            if (it != null) {
                val bundle = Bundle()
                bundle.putInt(ID_BUNDLE_SETS, it.setModel.id)
                bundle.putString(SET_NAME_BUNDLE_SETS, it.setModel.setName)

                navigateWithBundle(it.destination, bundle)
                viewModel.userNavigatedToSet()
            }
        }
    }

    override fun OnDeleteSetClicked(id: Int) {
        viewModel.deleteSet(id)
    }


}