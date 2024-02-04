package com.joaovitormo.myguests.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaovitormo.myguests.model.GuestModel
import com.joaovitormo.myguests.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = GuestRepository.getInstance(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()

    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll(){
        listAllGuests.value= repository.getAll()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}