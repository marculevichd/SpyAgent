package com.example.spyagent.presentation.view.fragments.mainmenu.sets

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.spyagent.R

class MyDialogFragment (val word:String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val editText = EditText(requireContext())
        editText.setText(word)
        editText.gravity = 0x11

        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.text_new_value))
            .setCancelable(false)
            .setView(editText)
            .setPositiveButton(getString(R.string.Confirm)) { _, _ ->
                if(word.isNullOrEmpty()){
                    val callback  = getTargetFragment() as DialogFragmentCallbackInterface
                    callback.createNewWord(editText.text.toString())
                } else {
                    val callback  = getTargetFragment() as DialogFragmentCallbackInterface
                    callback.updateWord(word, editText.text.toString())
                }

            }
            .setNegativeButton(getString(R.string.Cancel)) { dialog, _ ->
                dialog.cancel()
            }

        return alertDialog.create()
    }

}