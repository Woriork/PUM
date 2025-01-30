package com.example.l3

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.parcelize.Parcelize

class E1 : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskListAdapter
    private val exerciseLists = DummyDataGenerator.generateExerciseLists()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_e1, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_tasks)

        adapter = TaskListAdapter(exerciseLists) { selectedList ->
            val action = E1Directions.actionE1ToE3(selectedList)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }
}

@Parcelize
data class Exercise(
    val content: String,
    val points: Int
) : Parcelable

@Parcelize
data class Subject(
    val name: String
) : Parcelable

@Parcelize
data class ExerciseList(
    val exercises: List<Exercise>,
    val subject: Subject,
    val grade: Double
) : Parcelable

object DummyDataGenerator {
    private val subjects = listOf(
        Subject("Matematyka"),
        Subject("PUM"),
        Subject("Fizyka"),
        Subject("Elektronika"),
        Subject("Algorytmy")
    )
    fun generateUrszulkaString(): String {
        val trenWords = listOf(
            "Urszulo", "moja", "śliczna", "safo", "wszystek", "święty", "Kościół",
            "powściągnąć", "łzy", "ludzka", "natury", "przepaść", "gorzkie", "płacze",
            "rozważam", "los", "nieprzewidzone", "opatrzności", "klęska", "smutku",
            "żalu", "utracona", "świat", "dziewka", "cnoty", "wdzięczność", "płomień"
        )
        val sentenceLength = (5..15).random()
        return (1..sentenceLength)
            .joinToString(" ") { trenWords.random() }
            .capitalize() + "."
    }

    fun generateExerciseLists(): List<ExerciseList> {
        return List(20) {
            val numTasks = (1..10).random()
            val exercises = List(numTasks) { index ->
                Exercise(
                    content = "Zadanie ${index + 1}: ${generateUrszulkaString()}",
                    points = (1..10).random()
                )
            }
            ExerciseList(
                exercises = exercises,
                subject = subjects.random(),
                grade = (3..5).map { it + listOf(0.0, 0.5).random() }.random()
            )
        }
    }
}
