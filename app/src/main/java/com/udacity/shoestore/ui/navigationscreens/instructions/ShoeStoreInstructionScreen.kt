package com.udacity.shoestore.ui.navigationscreens.instructions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeStoreInstructionScreenBinding


class ShoeStoreInstructionScreen : Fragment() {
    //late-init view binding instance.
    private lateinit var binding: FragmentShoeStoreInstructionScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_shoe_store_instruction_screen, container, false
            )
        Log.d(TAG,"OnCreate View of Instruction Screen is called.")
        binding.instructionButton.setOnClickListener {
            Log.d(TAG,"Navigate to Shoe Listing Screen")
            Navigation.findNavController(binding.root)
                .navigate(ShoeStoreInstructionScreenDirections.actionShoeStoreInstructionScreenToShoeStoreListingScreen())
        }
        // Inflate the layout for this fragment.
        return binding.root
    }

    companion object{
        private final val TAG:String?=ShoeStoreInstructionScreen::class.java.simpleName
    }

}