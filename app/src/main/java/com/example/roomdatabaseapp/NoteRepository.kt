package com.example.roomdatabaseapp

import androidx.lifecycle.LiveData

class NoteRepository (private val dao: NoteDao) {

    val allNote : LiveData<List<NoteEntity>> = dao.getAllNote()

    suspend fun insert(note: NoteEntity){
        dao.insert(note)
    }
    suspend fun delete(note: NoteEntity) {
        dao.delete(note)
    }
    suspend fun update(note: NoteEntity) {
        dao.update(note)
    }
}