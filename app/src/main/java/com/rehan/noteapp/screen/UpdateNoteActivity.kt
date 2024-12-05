package com.rehan.noteapp.screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rehan.noteapp.R
import com.rehan.noteapp.databinding.ActivityAddNoteBinding
import com.rehan.noteapp.databinding.ActivityUpdateNoteBinding
import com.rehan.noteapp.helper.NoteDatabaseHelper
import com.rehan.noteapp.model.ModelNote

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NoteDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.etEditJudul.setText(note.title)
        binding.etEditCatatan.setText(note.content)

        binding.btnUpdateNote.setOnClickListener {
            val newTitle = binding.etEditJudul.text.toString()
            val newContent = binding.etEditCatatan.text.toString()

            val updatedNote = ModelNote(noteId, newTitle, newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()

        }
    }
}