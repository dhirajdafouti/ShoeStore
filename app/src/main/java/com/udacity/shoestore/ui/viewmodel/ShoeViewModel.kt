package com.udacity.shoestore.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.EMPTY

class ShoeViewModel : ViewModel() {
    companion object {
        private final val TAG: String? = ShoeViewModel::class.java.simpleName
    }

    //shoe item property.
    private val _shoeItem = MutableLiveData<Shoe>()

    //getter method of _shoe property.
    val newShoeItem: LiveData<Shoe>
        get() = _shoeItem

    //_shoeList property
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()

    //getter method of ShoeList displayed on the listscreen
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList


    init {
        Log.d(TAG,"View Model class variable initialization ")
        _shoeItem.value = Shoe(EMPTY, 0.0, EMPTY, EMPTY)
        _shoeList.value = mutableListOf()
    }

    /**
     * The method will add the shoe details onto the Show List screen.
     * The method will be called once the Save button is pressed.
     */
    fun addItemToList() {
        Log.i(TAG, "New Shoe Data is added!!")
        _shoeList.value?.add(_shoeItem.value!!)
    }

    /**
     * The method will discard the details.
     * The method method will be called once the cancel button is called.
     * Case :Once the user enter the details after cancel button is pressed.
     */
    fun discard() {
        Log.i(TAG, "New Shoe Data is Discarded!!")
        _shoeItem.value = Shoe(EMPTY, 0.0, EMPTY, EMPTY)
    }

    override fun onCleared() {
        Log.i(TAG, "Model View Destroyed!!")
        super.onCleared()
    }

    /**
     * The method will check the size of shoe list.
     * Based on the size the list will be populated.
     */
    fun shoeListEmpty(): Boolean {
        return _shoeList.value?.size!! > 0
    }
}