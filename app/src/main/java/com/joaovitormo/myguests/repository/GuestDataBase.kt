package com.joaovitormo.myguests.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.VERSION

class GuestDataBase(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int
) : SQLiteOpenHelper(context, NAME, factory, VERSION) {

    companion object {
        private const val  NAME = "guestdb"
        private const val  VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Criação do banco
        db.execSQL("create table Guest (id integer primary key autoincrement, name text, presence integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}