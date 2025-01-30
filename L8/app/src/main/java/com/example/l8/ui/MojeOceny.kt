package com.example.l8.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.l8.viewmodel.GradeViewModel
import com.example.l8.viewmodel.GradeViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import android.app.Application
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner

@Composable
fun MojeOceny(navController: NavHostController, modifier: Modifier = Modifier) {
    val viewModel: GradeViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "GradeViewModel",
        GradeViewModelFactory(LocalContext.current.applicationContext as Application)
    )
    val grades by viewModel.gradesState.collectAsStateWithLifecycle()

    val avg = grades.map { it.value }.average()

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = { Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Moje oceny", fontSize = 24.sp)
        }},
        content = { innerPadding ->
            Column (
                modifier = Modifier.padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().weight(0.7f)
                ) {
                    items(grades.size) {
                        Text(
                            text = "${grades[it].name} ${grades[it].value}",
                            fontSize = 32.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("edytuj/${grades[it].id}")
                                }
                                .padding(2.dp)
                        )
                    }
                }
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Średnia ocen: ", fontSize = 20.sp)
                    if (avg.isNaN()) {
                        Text("Brak ocen", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                    else { Text(String.format("%.2f", avg), fontSize = 20.sp, fontWeight = FontWeight.Bold) }

                }
                Button(onClick = {navController.navigate(Screens.DodajNowy.route)}, modifier = Modifier.fillMaxWidth().height(60.dp) ) {
                    Text("Nowy wpis", fontSize = 20.sp)
                }
            }
        },
    )
}