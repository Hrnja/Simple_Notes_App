package com.example.roomdatabaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class AddNote : AppCompatActivity() {
    private lateinit var addTitle:EditText
    private lateinit var addDescription:EditText
    private lateinit var addButton : Button
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        addTitle = findViewById(R.id.IDTitleAdd)
        addDescription = findViewById(R.id.IDDescriptionAdd)
        addButton = findViewById(R.id.IDButtonAdd)

        noteViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)



        addButton.setOnClickListener {
            val title = addTitle.text.toString()
            val description = addDescription.text.toString()

            noteViewModel.addNote(NoteEntity(title, description))
            Toast.makeText(this, "Note Added", Toast.LENGTH_LONG).show()

            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }

    }
}