package com.joaovitormo.myguests.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.VERSION
import com.joaovitormo.myguests.constants.DataBaseConstants

class GuestDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val  NAME = "guestdb"
        private const val  VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Criação do banco
        db.execSQL("create table "+ DataBaseConstants.GUEST.TABLE_NAME +" " +
                "("+ DataBaseConstants.COLUMNS.ID+" integer primary key autoincrement, " +
                ""+ DataBaseConstants.COLUMNS.NAME+" text, " +
                ""+ DataBaseConstants.COLUMNS.PRESENCE+" integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}