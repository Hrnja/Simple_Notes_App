package com.example.roomdatabaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class EditeNote : AppCompatActivity() {
    private lateinit var titleNote:EditText
    private lateinit var descriptionNote:EditText
    private lateinit var buttonAddUpdate:Button
    lateinit var noteViewModel: NoteViewModel
    var idNote = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edite_note)

        titleNote = findViewById(R.id.IDTitle)
        descriptionNote = findViewById(R.id.IDDescription)
        buttonAddUpdate = findViewById(R.id.IDButton)

        noteViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)



        titleNote.setText(intent.getStringExtra("Title"))
        descriptionNote.setText(intent.getStringExtra("Description"))
        idNote = intent.getIntExtra("IDNote", -1)

        buttonAddUpdate.setOnClickListener {
            val title = titleNote.text.toString()
            val description = descriptionNote.text.toString()

            val update = NoteEntity(title, description)
            update.id = idNote
            noteViewModel.updateNote(update)
            Toast.makeText(this, "Note Updated", Toast.LENGTH_LONG).show()

            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }

    }
}