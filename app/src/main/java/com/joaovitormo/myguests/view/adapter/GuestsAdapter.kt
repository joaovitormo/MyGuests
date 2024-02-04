package com.joaovitormo.myguests.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.joaovitormo.myguests.databinding.RowGuestBinding
import com.joaovitormo.myguests.model.GuestModel
import com.joaovitormo.myguests.view.listener.OnGuestListener
import com.joaovitormo.myguests.view.viewholder.GuestViewHolder

class GuestsAdapter : RecyclerView.Adapter<GuestViewHolder>(){

    private var guestList: List<GuestModel> = listOf()

    private lateinit var listener: OnGuestListener
    //Cria as linhas ds recyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
       val item =  RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GuestViewHolder(item, listener)
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    //Atribui valores
    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    fun updatedGuests(list: List<GuestModel>) {
        guestList = list
        notifyDataSetChanged()
    }

    fun attachListener(guestListener: OnGuestListener){
        listener = guestListener
    }
}