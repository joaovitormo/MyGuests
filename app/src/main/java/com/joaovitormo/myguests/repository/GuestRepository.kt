package com.joaovitormo.myguests.repository

import android.content.ContentValues
import android.content.Context
import com.joaovitormo.myguests.constants.DataBaseConstants
import com.joaovitormo.myguests.model.GuestModel
import java.lang.Exception

//O construtor precisa ser privado para que não tenha muitas instancias da mesma classe
class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)
    //Singleton - Acesso as intancias da nossa classe - controlar o nº de instancias
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }

    }

    fun insert(guest: GuestModel) : Boolean{
        return try {
            val presence = if(guest.presence) 1 else 0

            val db = guestDataBase.writableDatabase
            val values = ContentValues()
            values.put(DataBaseConstants.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun update(guest: GuestModel): Boolean{
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if(guest.presence) 1 else 0
            val values = ContentValues()
            values.put(DataBaseConstants.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.COLUMNS.PRESENCE, presence)
            val selection = DataBaseConstants.COLUMNS.ID + "= ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)
            true
        } catch (e: Exception){
            false
        }

    }

    fun delete(id: Int): Boolean{
        return try {
            val db = guestDataBase.writableDatabase


            val selection = DataBaseConstants.COLUMNS.ID + "= ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception){
            false
        }
    }

    fun getAll() : List<GuestModel>{
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.COLUMNS.ID,
                DataBaseConstants.COLUMNS.NAME,
                DataBaseConstants.COLUMNS.PRESENCE
            )
            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME, projection, null, null, null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMNS.PRESENCE))


                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()

        } catch (e: Exception){
            return list
        }
        return list
    }
    fun getPresence() : List<GuestModel>{
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase

            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence=1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMNS.PRESENCE))


                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()

        } catch (e: Exception){
            return list
        }
        return list
    }
    fun getAbsent() : List<GuestModel>{
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase

            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence=0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMNS.PRESENCE))


                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()

        } catch (e: Exception){
            return list
        }
        return list
    }
}