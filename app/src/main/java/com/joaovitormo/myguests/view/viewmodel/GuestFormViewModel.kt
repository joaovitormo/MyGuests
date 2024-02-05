package com.joaovitormo.myguests.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaovitormo.myguests.model.GuestModel
import com.joaovitormo.myguests.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest : LiveData<GuestModel> = guestModel

    fun insert(guest: GuestModel){
        repository.insert(guest)
    }

    fun get(id : Int) {
        repository.get(id)
    }

}