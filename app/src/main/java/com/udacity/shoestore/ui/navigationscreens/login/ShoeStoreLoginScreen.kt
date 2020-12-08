package com.udacity.shoestore.ui.navigationscreens.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeStoreLoginScreenBinding


class ShoeStoreLoginScreen : Fragment() {
    //late-init view binding instance.
    private lateinit var binding: FragmentShoeStoreLoginScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_store_login_screen, container, false
        )
        Log.d(TAG,"OnCreate view of Login Screen called!!!")
        binding.btnLogin.setOnClickListener {
            Log.d(TAG,"Navigate to Welcome Screen from Login Screen..")
            Navigation.findNavController(binding.root)
                .navigate(ShoeStoreLoginScreenDirections.actionShoeStoreLoginScreenToShoeStoreWelcomeScreen())
        }
        binding.account.setOnClickListener {
            Log.d(TAG,"Navigate to Welcome Screen from Login Screen..")
            Navigation.findNavController(binding.root)
                .navigate(ShoeStoreLoginScreenDirections.actionShoeStoreLoginScreenToShoeStoreWelcomeScreen())
        }
        // Inflate the layout for this fragment.
        return binding.root
    }
    companion object{
        private final val TAG:String?=ShoeStoreLoginScreen::class.java.simpleName
    }

}