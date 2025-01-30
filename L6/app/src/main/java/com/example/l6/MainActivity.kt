package com.example.l6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.l6.ui.theme.L6Theme
import kotlin.random.Random

// Dane
data class Exercise(val tresc: String, val punkty: Int)
data class Subject(val name: String)
data class Summary(val przedmiot: Subject, val srednia: Double, val wystapienia: Int)
data class ExerciseList(val exercises: MutableList<Exercise>, val subject: Subject, val grade: Float)

val listyZadan = mutableListOf<ExerciseList>()
val rosemary = mutableListOf<Summary>()

val Subjects = mutableListOf(
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


// Generowanie listy zadań
fun generatorZadan() {
    for (i in 1..20) {
        val exercises = MutableList(Random.nextInt(1, 10)) {
            Exercise(
                tresc = "Zadanie ${i + 1}: ${generateUrszulkaString()}",
                punkty = (1..10).random()
            )
        }
        listyZadan.add(
            ExerciseList(
                exercises = exercises,
                subject = Subjects[Random.nextInt(Subjects.size)],
                grade = (Random.nextInt(6, 11)).toFloat() / 2
            )
        )
    }
}

// Podsumowanie list
fun podsumujListy() {
    rosemary.clear()
    for (subject in Subjects) {
        val filteredLists = listyZadan.filter { it.subject.name == subject.name }
        val wystapienia = filteredLists.size
        val srednia = if (wystapienia > 0) filteredLists.sumOf { it.grade.toDouble() } / wystapienia else 0.0
        if (wystapienia > 0) {
            rosemary.add(Summary(subject, srednia, wystapienia))
        }
    }
}

// Ekrany
sealed class Screens(val route: String) {
    data object E1 : Screens("E1")
    data object E2 : Screens("E2")
    data object E3 : Screens("E3/{index}")
}

sealed class BottomBar(val route: String, val title: String, val icon: Int) {
    data object E1 : BottomBar(Screens.E1.route, "Listy zadań", R.drawable.lista)
    data object E2 : BottomBar(Screens.E2.route, "Oceny", R.drawable.ocena)
}

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        generatorZadan()
        podsumujListy()

        enableEdgeToEdge()
        setContent {
            L6Theme  {
                Scaffold(
                    content = { Navigation() }
                )
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomMenu(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.E1.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.E1.route) { E1(navController) }
            composable(Screens.E2.route) { E2() }
            composable(
                Screens.E3.route,
                arguments = listOf(navArgument("index") { type = NavType.IntType })
            ) { backStackEntry ->
                val index = backStackEntry.arguments?.getInt("index")
                if (index != null && index in listyZadan.indices) {
                    E3(index)
                } else {
                    Text("Nie znaleziono listy zadań.")
                }
            }
        }
    }
}

@Composable
fun BottomMenu(navController: NavHostController) {
    val screens = listOf(BottomBar.E1, BottomBar.E2)
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                label = { Text(screen.title) },
                icon = { Icon(getImageVector(screen.icon), contentDescription = null) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = { navController.navigate(screen.route) }
            )
        }
    }
}

@Composable
fun getImageVector(iconResId: Int): ImageVector {
    return ImageVector.vectorResource(id = iconResId)
}

@Composable
fun E1(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Moje Listy Zadań", fontSize = 30.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(20.dp))
        LazyColumn {
            items(listyZadan.size) { index ->
                val exerciseList = listyZadan[index]
                val count = listyZadan.filter { it.subject == exerciseList.subject }.indexOf(exerciseList) + 1
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray)
                        .clickable { navController.navigate("E3/$index") }
                        .padding(16.dp)
                ) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(exerciseList.subject.name, fontSize = 20.sp)
                        Text("Lista: $count", fontSize = 20.sp)
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Ilość zadań: ${exerciseList.exercises.size}")
                        Text("Ocena: ${exerciseList.grade}")
                    }
                }
            }
        }
    }
}

@Composable
fun E2() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Moje Oceny", fontSize = 30.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(20.dp))
        LazyColumn {
            items(rosemary.size) { index ->
                val summary = rosemary[index]
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray)
                        .padding(16.dp)
                ) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(summary.przedmiot.name, fontSize = 20.sp)
                        Text("Śr. ocena: %.2f".format(summary.srednia))
                    }
                    Text("Liczba list: ${summary.wystapienia}")
                }
            }
        }
    }
}

@Composable
fun E3(index: Int) {
    val exerciseList = listyZadan[index]
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "${exerciseList.subject.name} - Lista ${index + 1}",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(20.dp)
        )
        LazyColumn {
            items(exerciseList.exercises.size) { i ->
                val exercise = exerciseList.exercises[i]
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray)
                        .padding(16.dp)
                ) {
                    Text("Zadanie ${i + 1}", fontSize = 20.sp)
                    Text("Punkty: ${exercise.punkty}")
                    Text(exercise.tresc)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    L6Theme  {
        Navigation()
    }
}
