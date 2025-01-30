package com.example.l7.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.l7.Model.User
import com.example.l7.ViewModel.UserViewModel

@Composable
fun ViewList(viewModel: UserViewModel, navController: NavController) {
    val users = viewModel.users
    LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        items(users) { user ->
            Text(
                text = "${user.firstName} ${user.lastName}",
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickable {
                        navController.navigate("details/${user.index}")
                    }
            )
        }
    }
}