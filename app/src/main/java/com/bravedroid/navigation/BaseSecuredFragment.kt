package com.bravedroid.navigation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

private const val TAG: String = "BaseSecuredFragment"

abstract class BaseSecuredFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.authenticationState.observe(
            viewLifecycleOwner,
            Observer { authenticationState: LoginViewModel.AuthenticationState ->
                when (authenticationState) {
                    LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                        Log.d(TAG, "AUTHENTICATED")
                    }
                    LoginViewModel.AuthenticationState.UNAUTHENTICATED, LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> {
                        findNavController().navigate(R.id.action_global_loginFragment)
                        Log.d(TAG, "UNAUTHENTICATED or INVALID_AUTHENTICATION ")
                    }
                }
            })
    }
}
