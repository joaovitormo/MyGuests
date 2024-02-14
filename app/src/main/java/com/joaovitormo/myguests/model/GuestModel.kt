package com.joaovitormo.myguests.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guest")
class GuestModel() {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Int = 0

    @ColumnInfo(name="name")
    var name: String = ""

    @ColumnInfo(name="presence")
    var presence: Boolean = false
}