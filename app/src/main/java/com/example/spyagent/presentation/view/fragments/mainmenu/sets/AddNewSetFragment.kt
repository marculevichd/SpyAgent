package com.example.spyagent.presentation.view.fragments.mainmenu.sets

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spyagent.R
import com.example.spyagent.databinding.FragmentAddNewSetBinding
import com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter.NewSetAdapter
import com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter.NewSetListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewSetFragment : Fragment(), NewSetListener {


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
            val editText = EditText(requireContext())

            val alertDialog = AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.enter_new_word))
                .setCancelable(false)
                .setView(editText)
                .setPositiveButton(getString(R.string.Confirm)) { _, _ ->
                    if (!editText.text.isNullOrEmpty()) {
                        viewModel.addNewWordNewSet(editText.text.toString())
                    }
                }
                .setNegativeButton(getString(R.string.Cancel)) { dialog, _ ->
                    dialog.cancel()
                }
            alertDialog.show()
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
        val editText = EditText(requireContext())
        editText.setText(word)

        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.text_new_value))
            .setCancelable(false)
            .setView(editText)
            .setPositiveButton(getString(R.string.Confirm)) { _, _ ->
                if (!editText.text.isNullOrEmpty()) {
                    viewModel.updateWordNewSet(word, editText.text.toString())
                }
            }
            .setNegativeButton(getString(R.string.Cancel)) { dialog, _ ->
                dialog.cancel()
            }
        alertDialog.show()
    }

    override fun deleteWord(word: String) {
        viewModel.removeWordNewSet(word)
    }
}