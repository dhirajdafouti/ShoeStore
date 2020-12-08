package com.udacity.shoestore.ui.navigationscreens.shoedetails

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeStoreDetailsScreenBinding
import com.udacity.shoestore.ui.viewmodel.ShoeViewModel


class ShoeStoreDetailsScreen : Fragment() {
    //late init binding instance
    private lateinit var binding: FragmentShoeStoreDetailsScreenBinding

    //view model instance.
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_store_details_screen, container, false
        )
        Log.i(TAG, "On create of ShoeDetails Screen is called")
        clearTheWidgets()
        setShoeDetailsTextWatchers()
        binding.lifecycleOwner = this
        binding.showViewModel = viewModel
        return binding.root
    }

    /**
     * Clearing the Widgets once the user Navigation to Shoe Details Screen.
     * Case:If it is prefilled.
     */
    private fun clearTheWidgets() {
        Log.i(TAG, "Clearing the Widgets")
        binding.showViewModel?.discard()
        binding.etCompanyName.text?.clear()
        binding.etShoeSize.text?.clear()
        binding.etShoeDescription.text?.clear()
        binding.etShoeSize.text?.clear()
    }

    /**
     * This method will listen the text changes for the Shoe Details Screen widgets.
     */
    private fun setShoeDetailsTextWatchers() {
        binding.etShoename.addTextChangedListener(shoeDetailsWatcher)
        binding.etCompanyName.addTextChangedListener(shoeDetailsWatcher)
        binding.etShoeSize.addTextChangedListener(shoeDetailsWatcher)
        binding.etShoeDescription.addTextChangedListener(shoeDetailsWatcher)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancelButton.setOnClickListener {
            Log.i(TAG, "Navigating to Shoe Listing Screen on Cancel")
            Navigation.findNavController(binding.root)
                .navigate(ShoeStoreDetailsScreenDirections.actionShoeStoreDetailsScreenToShoeStoreListingScreen())
            binding.showViewModel?.discard()
        }
        binding.saveButtom.setOnClickListener {
            Log.i(TAG, "Navigating to Shoe Listing Screen on Save")
            binding.showViewModel?.addItemToList()
            Toast.makeText(
                requireActivity(),
                context?.getString(R.string.data_saved),
                Toast.LENGTH_SHORT
            ).show()
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_shoeStoreDetailsScreen_to_shoeStoreListingScreen)
        }

    }

    /**
     * The Text Watcher Implementations.
     * This will enables the Save Button once all the text is filled.
     */
    private val shoeDetailsWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            //Implementation not needed.
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //Implementation not needed.
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (binding.etShoename.text.isNotEmpty()
                && binding.etCompanyName.text.isNotEmpty()
                && binding.etShoeSize.text.isNotEmpty()
                && binding.etShoeDescription.text.isNotEmpty()
            ) {
                binding.saveButtom.isEnabled = true
            }
        }

    }

    companion object {
        private final val TAG: String? = ShoeStoreDetailsScreen::class.java.simpleName
    }
}