package com.rio.responsi_mobile.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rio.responsi_mobile.model.TravelNote
import com.rio.responsi_mobile.utils.FirebaseHelper
import com.google.firebase.auth.FirebaseAuth
import com.rio.responsi_mobile.databinding.ActivityAddTravelNoteBinding


class AddTravelNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTravelNoteBinding
    private val firebaseHelper = FirebaseHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTravelNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveNoteButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()
            val location = binding.locationEditText.text.toString()

            if (title.isNotBlank() && description.isNotBlank() && location.isNotBlank()) {
                val newNote = TravelNote(
                    title = title,
                    description = description,
                    location = location,
                    date = System.currentTimeMillis().toString()  // Use current time as the date
                )

                // Mendapatkan UID pengguna yang sedang login
                val currentUser = FirebaseAuth.getInstance().currentUser
                val userId = currentUser?.uid

                if (userId != null) {
                    firebaseHelper.addTravelNote(userId, newNote)
                    Toast.makeText(this, "Note added successfully!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
