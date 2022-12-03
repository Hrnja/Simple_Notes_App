package com.example.roomdatabaseapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoTable")
class NoteEntity (
    @ColumnInfo(name="title") val title:String,
    @ColumnInfo(name="description") val description: String)
{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}