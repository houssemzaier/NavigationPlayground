package com.bravedroid.navigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : BaseSecuredFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (!hasFoundAndDismissedDialogByTag(ConfirmationProfileDialogFragment.TAG)) {
                findNavController().popBackStack()
            }
        }


        confirmation_btn.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToConfirmationProfileDialogFragment())
        }

        confirmation_fullscreen_btn.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(
                    android.R.id.content,
                    ConfirmationProfileDialogFragment(),
                    ConfirmationProfileDialogFragment.TAG
                ).commit()
        }

        info_btn.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionGlobalInfoFragment())
        }
    }
}

private fun Fragment.hasFoundAndDismissedDialogByTag(tag: String): Boolean {
    val dialogFragment =
        requireActivity().supportFragmentManager.findFragmentByTag(tag) as? DialogFragment

    return if (dialogFragment != null && dialogFragment.isVisible) {
        dialogFragment.dismiss()
        true
    } else {
        false
    }
}


