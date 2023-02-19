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
import com.example.spyagent.databinding.FragmentSetDetailBinding
import com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter.SetDetailAdapter
import com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter.SetDetailListener
import com.example.spyagent.utils.Const.ID_BUNDLE_SETS
import com.example.spyagent.utils.Const.SET_NAME_BUNDLE_SETS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetDetailFragment : Fragment(), SetDetailListener {

    private val viewModel: SetDetailViewModel by viewModels()

    private var _viewBinding: FragmentSetDetailBinding? = null
    private val viewBinding get() = _viewBinding!!

    lateinit var setDetailAdapter: SetDetailAdapter

    lateinit var bundle: Bundle


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentSetDetailBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDetailAdapter = SetDetailAdapter(this)

        viewBinding.setDetailRecyclerView.layoutManager = LinearLayoutManager(context)
        viewBinding.setDetailRecyclerView.adapter = setDetailAdapter


        bundle = requireArguments()
        bundle.let {
            viewBinding.setDetailTitle.setText(it.getString(SET_NAME_BUNDLE_SETS))
            viewModel.getWords(it.getInt(ID_BUNDLE_SETS))
            viewModel.listWords.observe(viewLifecycleOwner) {
                setDetailAdapter.submitList(it)
            }
        }

        viewBinding.addWord.setOnClickListener {
            val editText = EditText(requireContext())

            val alertDialog = AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.enter_new_word))
                .setCancelable(false)
                .setView(editText)
                .setPositiveButton(getString(R.string.Confirm)) { _, _ ->
                    if (!editText.text.isNullOrEmpty()) {
                        viewModel.addNewWord(
                            bundle.getInt(ID_BUNDLE_SETS),
                            editText.text.toString()
                        )
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

        viewModel.updateSetName(
            bundle.getInt(ID_BUNDLE_SETS),
            viewBinding.setDetailTitle.text.toString()
        )

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
                    viewModel.updateWord(
                        bundle.getInt(ID_BUNDLE_SETS),
                        word,
                        editText.text.toString()
                    )
                }
            }
            .setNegativeButton(getString(R.string.Cancel)) { dialog, _ ->
                dialog.cancel()
            }
        alertDialog.show()
    }

    override fun deleteWord(word: String) {
        viewModel.removeWord(bundle.getInt(ID_BUNDLE_SETS), word)
    }
}