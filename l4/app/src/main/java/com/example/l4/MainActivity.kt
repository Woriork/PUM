package com.example.l4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.l4.ui.theme.L4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            L4Theme {
                QuizScreen()
            }
        }
    }
}

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

@Composable
fun QuizScreen() {
    val questions = listOf(
        Question("Jakie jest stolica Francji?", listOf("Paryż", "Londyn", "Berlin", "Madryt"), 0),
        Question("Ile to jest 2 + 2?", listOf("3", "4", "5", "6"), 1),
        Question("Która planeta jest największa?", listOf("Mars", "Ziemia", "Jowisz", "Wenus"), 2),
        Question("Kto napisał 'Rok 1984'?", listOf("Orwell", "Huxley", "Szekspir", "Tolkien"), 0),
        Question("Jak nazywa się największy ocean?", listOf("Spokojny", "Atlantycki", "Indyjski", "Arktyczny"), 0),
        Question("W którym roku człowiek wylądował na Księżycu?", listOf("1969", "1972", "1965", "1970"), 0),
        Question("Kto jest autorem 'Pana Tadeusza'?", listOf("Mickiewicz", "Sienkiewicz", "Rej", "Żeromski"), 0),
        Question("Ile nóg ma pająk?", listOf("6", "8", "10", "12"), 1),
        Question("Które zwierzę jest nazywane 'królem dżungli'?", listOf("Lew", "Tygrys", "Słoń", "Gepard"), 0),
        Question("Jaki jest symbol chemiczny wody?", listOf("H2O", "CO2", "O2", "HCl"), 0)
    )

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswerIndex by remember { mutableStateOf(-1) }
    var score by remember { mutableStateOf(0) }
    var showResult by remember { mutableStateOf(false) }

    if (showResult) {
        ResultScreen(score = score, totalQuestions = questions.size) {
            currentQuestionIndex = 0
            selectedAnswerIndex = -1
            score = 0
            showResult = false
        }
    } else {
        QuestionScreen(
            question = questions[currentQuestionIndex],
            questionNumber = currentQuestionIndex + 1,
            totalQuestions = questions.size,
            selectedAnswerIndex = selectedAnswerIndex,
            onAnswerSelected = { selectedAnswerIndex = it },
            onNextClicked = {
                if (selectedAnswerIndex == questions[currentQuestionIndex].correctAnswerIndex) {
                    score++
                }
                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                    selectedAnswerIndex = -1
                } else {
                    showResult = true
                }
            }
        )
    }
}

@Composable
fun QuestionScreen(
    question: Question,
    questionNumber: Int,
    totalQuestions: Int,
    selectedAnswerIndex: Int,
    onAnswerSelected: (Int) -> Unit,
    onNextClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pytanie $questionNumber z $totalQuestions",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        LinearProgressIndicator(
            progress = questionNumber.toFloat() / totalQuestions,
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFFB26ADA)
        )

        Text(
            text = question.questionText,
            fontSize = 20.sp,
            modifier = Modifier
                .background(Color(0xFFE0E0E0))
                .padding(8.dp)
                .fillMaxWidth()
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            question.options.forEachIndexed { index, option ->
                RadioButtonWithLabel(
                    text = option,
                    selected = selectedAnswerIndex == index,
                    onClick = { onAnswerSelected(index) }
                )
            }
        }

        Button(
            onClick = onNextClicked,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E4CAF))
        ) {
            Text("Następne", color = Color.White)
        }
    }
}

@Composable
fun RadioButtonWithLabel(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 16.sp)
    }
}

@Composable
fun ResultScreen(score: Int, totalQuestions: Int, onRetryClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Twój wynik: $score z $totalQuestions",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onRetryClicked,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8C4CAF))
        ) {
            Text("Zagraj jeszcze raz", color = Color.White)
        }
    }
}
