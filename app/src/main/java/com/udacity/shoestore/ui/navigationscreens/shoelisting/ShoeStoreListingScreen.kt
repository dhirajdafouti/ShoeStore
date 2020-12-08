package com.udacity.shoestore.ui.navigationscreens.shoelisting

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeStoreListingScreenBinding
import com.udacity.shoestore.databinding.ShoeSingleItemBinding
import com.udacity.shoestore.ui.viewmodel.ShoeViewModel
import kotlinx.android.synthetic.main.shoe_single_item.view.*
import androidx.navigation.findNavController as findNavController1

class ShoeStoreListingScreen : Fragment() {
    //late-init view binding instance.
    private lateinit var binding: FragmentShoeStoreListingScreenBinding

    //late-init for single view binding instance.
    private lateinit var singleItemBinding: ShoeSingleItemBinding

    //Activity view model instance
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_shoe_store_listing_screen, container, false
            )
        binding.newItemFloatingButton.setOnClickListener {
            Log.d(TAG, "Navigation to Shoe Detailed Screen ")
            Navigation.findNavController(binding.root)
                .navigate(ShoeStoreListingScreenDirections.actionShoeStoreListingScreenToShoeStoreDetailsScreen())
        }
        Log.d(TAG, "onCreate view of Shoe Listing Screen  is called")
        //The set option menu is appreciable for Shoe listing only.
        setHasOptionsMenu(true)
        binding.lifecycleOwner = this
        binding.shoeViewModel = viewModel
        viewModel.discard()
        //creating the Single Shoe Item.
        createShoeItem()
        // Inflate the layout for this fragment.
        return binding.root
    }

    /**
     * Creating the Shoe Item.
     */
    private fun createShoeItem() {
        Log.d(TAG, "New Shoe Item is created... ")
        viewModel.finalShoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            val shoeLayout: LinearLayout =
                binding.shoelistlayout
            if (viewModel.shoeListEmpty()) {
                if (binding.noShoeitemText.isVisible) {
                    binding.noShoeitemText.visibility = View.GONE
                }
                for (shoe in shoeList) {
                    singleItemBinding = DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.shoe_single_item,
                        shoeLayout,
                        false
                    )
                    singleItemBinding.root.shoeNameTextView.text = shoe.name
                    singleItemBinding.root.shoeSizeTextView.text = shoe.size.toString()
                    singleItemBinding.root.companyTextView.text = shoe.company
                    singleItemBinding.root.descriptionTextView.text = shoe.description

                    shoeLayout.addView(singleItemBinding.root)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflowmenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController1()
        ) || super.onOptionsItemSelected(item)
    }

    companion object {
        private final val TAG: String? = ShoeStoreListingScreen::class.simpleName
    }
}

