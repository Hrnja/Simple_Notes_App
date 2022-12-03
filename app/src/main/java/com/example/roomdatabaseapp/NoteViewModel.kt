package com.example.roomdatabaseapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNote : LiveData<List<NoteEntity>>
    val repository : NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getDao()
        repository = NoteRepository(dao)
        allNote = repository.allNote
    }

    fun addNote (note:NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
    fun deleteNote(note: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    fun updateNote(note: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
}