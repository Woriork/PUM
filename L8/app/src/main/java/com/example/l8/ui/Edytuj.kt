package com.example.l8.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.l8.data.Grade
import com.example.l8.viewmodel.GradeViewModel
import com.example.l8.viewmodel.GradeViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import android.app.Application
import androidx.compose.foundation.text.KeyboardOptions
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner

@Composable
fun Edytuj(navController: NavHostController, modifier: Modifier = Modifier, id: Int) {
    val viewModel: GradeViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "GradeViewModel",
        GradeViewModelFactory(LocalContext.current.applicationContext as Application)
    )

    LaunchedEffect(id) {
        viewModel.getGradeById(id)
    }

    val grade = viewModel.grade.value

    if (grade == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
        return
    }

    var textInput by remember { mutableStateOf(grade?.name.toString()) }
    var numberInput by remember { mutableStateOf(grade?.value.toString()) }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = { Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Edytuj ocenę", fontSize = 24.sp)
        }},
        content = { innerPadding ->
            Column (
                modifier = Modifier.fillMaxWidth().padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                OutlinedTextField(
                    value = textInput,
                    onValueChange = { textInput = it },
                    label = { Text("Nazwa przedmiotu") },
                    singleLine = true
                )

                OutlinedTextField(
                    value = numberInput,
                    onValueChange = { input ->
                        val regex = """^([1-4](\.\d{0,1})?|5(\.0?)?)$""".toRegex()
                        if (input.isEmpty() || regex.matches(input)) {
                            numberInput = input
                        }
                    },
                    label = { Text("Ocena") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 10.dp)
            ) {
                Button(onClick = {
                    if (textInput.isEmpty() || numberInput.isEmpty()) {
                        return@Button
                    }
                    else {
                        val upd = Grade(grade.id, textInput, numberInput.toDouble())
                        viewModel.update(upd)
                        navController.navigate(Screens.MojeOceny.route)
                    }
                }, modifier = Modifier.fillMaxWidth().height(70.dp).padding(bottom = 10.dp) ) {
                    Text("Edytuj wpis", fontSize = 20.sp)
                }
                Button(onClick = {
                    viewModel.delete(grade)
                    navController.navigate(Screens.MojeOceny.route) }, modifier = Modifier.fillMaxWidth().height(60.dp) ) {
                    Text("Usuń wpis", fontSize = 20.sp)
                }
            }
        },
    )
}