package com.example.l3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class E3 : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExerciseAdapter
    private val args: E3Args by navArgs() // Argumenty przekazane z E1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_e3, container, false)
        setupRecyclerView(view)
        return view
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_exercises)

        // Uzyskanie przekazanej listy zadań
        val selectedList = args.selectedList

        // Inicjalizacja adaptera z przekazanymi zadaniami
        adapter = ExerciseAdapter(selectedList.exercises)

        recyclerView.apply {
            adapter = this@E3.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
