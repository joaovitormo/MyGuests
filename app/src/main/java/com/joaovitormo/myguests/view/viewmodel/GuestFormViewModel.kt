package com.joaovitormo.myguests.view.viewmodel

import androidx.lifecycle.ViewModel
import com.joaovitormo.myguests.repository.GuestRepository

class GuestFormViewModel: ViewModel() {

    private val repository = GuestRepository()
}