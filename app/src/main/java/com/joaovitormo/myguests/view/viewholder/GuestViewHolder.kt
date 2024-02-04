package com.joaovitormo.myguests.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.joaovitormo.myguests.databinding.RowGuestBinding
import com.joaovitormo.myguests.model.GuestModel

class GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {
    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener {

        }
    }

}