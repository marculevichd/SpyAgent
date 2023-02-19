package com.example.spyagent.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

object NavHelper {

    fun Fragment.navigate(destination: Int){
        findNavController().navigate(destination)
    }

    fun Fragment.navigateWithDeleteBackStack(destinationId: Int, fragmentToDelete: Int) {
        val navOption = NavOptions.Builder()
        navOption.setPopUpTo(fragmentToDelete, true)
        findNavController().navigate(destinationId, null, navOption.build())
    }

    fun Fragment.navigateWithBundle(destinationId: Int, bundle: Bundle){
        findNavController().navigate(destinationId,bundle)
    }
}