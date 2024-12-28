package com.rio.responsi_mobile.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.rio.responsi_mobile.R
import com.rio.responsi_mobile.activities.AddTravelNoteActivity
import com.rio.responsi_mobile.activities.LoginActivity
import com.rio.responsi_mobile.activities.TravelNoteDetailActivity
import com.rio.responsi_mobile.adapter.TravelNoteAdapter
import com.rio.responsi_mobile.databinding.FragmentHomeBinding
import com.rio.responsi_mobile.model.TravelNote
import com.rio.responsi_mobile.utils.FirebaseHelper


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val firebaseHelper = FirebaseHelper()
    private val travelNotes = mutableListOf<TravelNote>()
    private lateinit var adapter: TravelNoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menggunakan View Binding untuk mengakses layout
        binding = FragmentHomeBinding.bind(view)

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = TravelNoteAdapter(travelNotes) { note ->
            // Menambahkan click listener pada item
            val intent = Intent(activity, TravelNoteDetailActivity::class.java)
            intent.putExtra("NOTE_ID", note.id)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter

        // Mendapatkan userId dari Firebase Authentication
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            // Load travel notes dari Firebase berdasarkan userId
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
            // Jika pengguna tidak login
            // Arahkan pengguna ke halaman login
            Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        // Navigasi ke Add Travel Note activity
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(activity, AddTravelNoteActivity::class.java)
            startActivity(intent)
        }
    }
}
