package com.example.roomdatabaseapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(
    val context: Context,
    val clickOnDeleteButton : ClickOnDeleteButton,
    val clickOnTextView : ClickOnTextView
) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private val allNote = ArrayList<NoteEntity>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle = itemView.findViewById<TextView>(R.id.titleRV)
        val deleteButton = itemView.findViewById<Button>(R.id.buttonRV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rec_look, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allNote.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.setText(allNote.get(position).title)

        holder.deleteButton.setOnClickListener {
            clickOnDeleteButton.onDelete(allNote.get(position))
        }

        holder.itemView.setOnClickListener{
            clickOnTextView.onClick(allNote.get(position))
        }
    }
    fun updateList(newList: List<NoteEntity>) {
        allNote.clear()
        allNote.addAll(newList)
        notifyDataSetChanged()
    }
}

interface ClickOnDeleteButton {
    fun onDelete(note:NoteEntity)
}
interface ClickOnTextView {
    fun onClick(note: NoteEntity)
}