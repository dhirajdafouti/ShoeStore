package com.udacity.shoestore.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityShoestoreBinding

class ShoeActivity : AppCompatActivity() {
    //late init variable for AppBarConfiguration,
    private lateinit var appBarConfiguration: AppBarConfiguration

    //late init variable for binding.
    private lateinit var binding: ActivityShoestoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityShoestoreBinding>(
            this,
            R.layout.activity_shoestore
        )
        Log.d(TAG, "On Create of Shoe activity is called")

        val navController =
            this.findNavController(R.id.myNavHostFragment)
        //This application is using the AppAction Bar
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {
        private final val TAG: String? = ShoeActivity::class.java.simpleName
    }

}