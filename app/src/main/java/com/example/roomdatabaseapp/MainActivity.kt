package com.example.roomdatabaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ClickOnDeleteButton, ClickOnTextView {
    private lateinit var recViw: RecyclerView
    private lateinit var addRV : ImageView
    lateinit var noteViewModel : NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addRV = findViewById(R.id.addRV)
        recViw = findViewById(R.id.recRV)

        recViw.layoutManager = LinearLayoutManager(this)

        val rvadapter = RVAdapter(this, this, this)
        recViw.adapter = rvadapter

        noteViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        noteViewModel.allNote.observe(this, Observer { list->
            list?.let {
                rvadapter.updateList(it)
            }
        })

        addRV.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNote::class.java)
            startActivity(intent)
            this.finish()
        }


    }
    override fun onDelete(note:NoteEntity) {
        noteViewModel.deleteNote(note)
        Toast.makeText(this,"${note.title} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onClick(note: NoteEntity) {
        val intent = Intent(this@MainActivity, EditeNote::class.java)
        intent.putExtra("Title", note.title)
        intent.putExtra("Description", note.description)
        intent.putExtra("IDNote", note.id)
        startActivity(intent)
        this.finish()
    }
}