package com.joaovitormo.myguests.view.viewholder

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.joaovitormo.myguests.databinding.RowGuestBinding
import com.joaovitormo.myguests.model.GuestModel
import com.joaovitormo.myguests.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {
    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja excluir o convidado?")
                .setPositiveButton("Sim") { p0, p1 -> listener.onDelete(guest.id) }
                .setNegativeButton("Não", null)
                .create()
                .show()
        listener.onDelete(guest.id)
        true
        }
    }

}