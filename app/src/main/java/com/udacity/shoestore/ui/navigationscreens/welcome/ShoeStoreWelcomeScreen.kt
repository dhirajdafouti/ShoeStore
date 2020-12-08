package com.udacity.shoestore.ui.navigationscreens.welcome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeStoreWelcomeScreenBinding


class ShoeStoreWelcomeScreen : Fragment() {
    //late-init view binding instance.
    private lateinit var binding: FragmentShoeStoreWelcomeScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_store_welcome_screen, container, false
        )
        Log.d(TAG, "OnCreate View of Welcome Screen is called.")

        binding.welcomeScreen.setOnClickListener {
            Log.d(TAG, "Navigate to Instruction Screen.")
            Navigation.findNavController(binding.root)
                .navigate(ShoeStoreWelcomeScreenDirections.actionShoeStoreWelcomeScreenToShoeStoreInstructionScreen())
        }
        // Inflate the layout for this fragment.
        return binding.root
    }

    companion object {
        private final val TAG: String? = ShoeStoreWelcomeScreen::class.java.simpleName
    }
}