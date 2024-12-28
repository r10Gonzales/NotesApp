package com.rio.responsi_mobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rio.responsi_mobile.R
import com.rio.responsi_mobile.adapter.TravelNoteAdapter
import com.rio.responsi_mobile.databinding.ActivityMainBinding
import com.rio.responsi_mobile.model.TravelNote
import com.rio.responsi_mobile.utils.FirebaseHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val firebaseHelper = FirebaseHelper()
    private val travelNotes = mutableListOf<TravelNote>()
    private lateinit var adapter: TravelNoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TravelNoteAdapter(travelNotes) { note ->
            val intent = Intent(this, TravelNoteDetailActivity::class.java)
            intent.putExtra("NOTE_ID", note.id)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter

        // Setup BottomNavigationView with NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        // Load travel notes from Firebase
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            firebaseHelper.getTravelNotes(userId).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    travelNotes.clear()
                    snapshot.children.forEach { child ->
                        val note = child.getValue(TravelNote::class.java)
                        if (note != null) {
                            travelNotes.add(note)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        } else {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // FloatingActionButton to add new travel note
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddTravelNoteActivity::class.java))
        }
    }
}