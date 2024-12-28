package com.rio.responsi_mobile.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.rio.responsi_mobile.activities.AddTravelNoteActivity
import com.rio.responsi_mobile.activities.TravelNoteDetailActivity
import com.rio.responsi_mobile.adapter.TravelNoteAdapter
import com.rio.responsi_mobile.databinding.FragmentHomeBinding
import com.rio.responsi_mobile.model.TravelNote
import com.rio.responsi_mobile.utils.FirebaseHelper

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val firebaseHelper = FirebaseHelper()
    private val travelNotes = mutableListOf<TravelNote>()
    private lateinit var adapter: TravelNoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TravelNoteAdapter(travelNotes) { note ->
            val intent = Intent(context, TravelNoteDetailActivity::class.java)
            intent.putExtra("NOTE_ID", note.id)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter

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
        }

        binding.addNoteButton.setOnClickListener {
            val intent = Intent(context, AddTravelNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
