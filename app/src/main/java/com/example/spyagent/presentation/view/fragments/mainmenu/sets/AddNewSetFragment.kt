package com.example.spyagent.presentation.view.fragments.mainmenu.sets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spyagent.R
import com.example.spyagent.databinding.FragmentAddNewSetBinding
import com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter.NewSetAdapter
import com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter.NewSetListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewSetFragment : Fragment(), NewSetListener, DialogFragmentCallbackInterface {


    private val viewModel: AddNewSetViewModel by viewModels()

    private var _viewBinding: FragmentAddNewSetBinding? = null
    private val viewBinding get() = _viewBinding!!

    lateinit var newSetAdapter: NewSetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentAddNewSetBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newSetAdapter = NewSetAdapter(this)

        viewBinding.setDetailRecyclerView.layoutManager = LinearLayoutManager(context)
        viewBinding.setDetailRecyclerView.adapter = newSetAdapter

        viewModel.createNewSet()
        viewModel.listWordsNewSet.observe(viewLifecycleOwner) {
            newSetAdapter.submitList(it.listWords)
        }

        viewBinding.addWord.setOnClickListener {
            val dialogFragment = MyDialogFragment("")
            dialogFragment.setTargetFragment(this, 0)
            dialogFragment.show(parentFragmentManager, getString(R.string.tagAddWordNewSet))
        }
    }


    override fun onStop() {
        super.onStop()
        if (viewBinding.setDetailTitle.text.toString().isNotEmpty()) {
            viewModel.updateNewSetName(
                viewBinding.setDetailTitle.text.toString()
            )
        }

    }

    override fun updateWord(word: String) {

        val dialogFragment = MyDialogFragment(word)
        dialogFragment.setTargetFragment(this, 0)
        dialogFragment.show(parentFragmentManager, getString(R.string.tagUpdateWordNewSet))

    }

    override fun deleteWord(word: String) {
        viewModel.removeWordNewSet(word)
    }

    override fun updateWord(oldWord: String, newWord: String) {
        viewModel.updateWordNewSet(oldWord, newWord)
    }

    override fun createNewWord(newWord: String) {
        viewModel.addNewWordNewSet(newWord)
    }
}