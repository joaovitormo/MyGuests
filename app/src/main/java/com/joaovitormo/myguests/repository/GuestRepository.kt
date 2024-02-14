package com.joaovitormo.myguests.repository

import android.content.ContentValues
import android.content.Context
import com.joaovitormo.myguests.constants.DataBaseConstants
import com.joaovitormo.myguests.model.GuestModel
import java.lang.Exception

//O construtor precisa ser privado para que não tenha muitas instancias da mesma classe
class GuestRepository(context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()

    //Singleton - Acesso as intancias da nossa classe - controlar o nº de instancias


    fun insert(guest: GuestModel): Boolean {
        return guestDataBase.insert(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return guestDataBase.update(guest) > 0
    }

    fun delete(id: Int) {
        val guest = get(id)
        return guestDataBase.delete(guest)
    }

    fun getAll(): List<GuestModel> {
        return guestDataBase.getAll()
    }

    fun get(id: Int): GuestModel {
        return guestDataBase.get(id)
    }

    fun getPresent(): List<GuestModel> {
        return guestDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return guestDataBase.getAbsent()
    }
}