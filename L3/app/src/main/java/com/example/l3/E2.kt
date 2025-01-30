package com.example.l3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class E2 : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScoreListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_e2, container, false)
        setupRecyclerView(view)
        return view
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_scores)

        val groupedData = DummyDataGenerator.generateExerciseLists()
            .groupBy { it.subject.name }
            .map { (subject, lists) ->
                val averageGrade = lists.map { it.grade }.average()
                subject to averageGrade // użycie pary zamiast Pair dla lepszej czytelności
            }

        adapter = ScoreListAdapter(groupedData)
        recyclerView.apply {
            adapter = this@E2.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
