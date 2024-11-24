package kg.iuca.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kg.iuca.lab1.ui.theme.Lab1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator()
                }
            }
        }
    }
}

@Composable
fun AppNavigator() {
    var currentScreen by remember { mutableStateOf(1) }

    when (currentScreen) {
        1 -> SimpleInterface { currentScreen = 2 }
        2 -> ClickCounter { currentScreen = 1 }
    }
}

@Composable
fun SimpleInterface(onSwitchToCounter: () -> Unit) {
    var text by remember { mutableStateOf("Привет, мир!") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { text = "Кнопка нажата" }) {
            Text("Нажми на меня")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onSwitchToCounter) {
            Text("Перейти к счетчику")
        }
    }
}

@Composable
fun ClickCounter(onSwitchToSimple: () -> Unit) {
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Количество нажатий: $count", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text("Увеличить счетчик")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onSwitchToSimple) {
            Text("Перейти к тексту и кнопке")
        }
    }
}
