package com.joaovitormo.myguests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.joaovitormo.myguests.model.GuestModel
import com.joaovitormo.myguests.R
import com.joaovitormo.myguests.constants.DataBaseConstants
import com.joaovitormo.myguests.databinding.ActivityGuestFormBinding
import com.joaovitormo.myguests.view.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        loadData()

    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(0,name, presence)
            viewModel.insert(model)
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            val guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)

        }
    }
}