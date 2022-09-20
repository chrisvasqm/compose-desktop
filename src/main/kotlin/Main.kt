import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isButtonEnabled = user.isNotBlank() && password.isNotBlank()

    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                label = { Text("Usuario") },
                value = user,
                onValueChange = { user = it }
            )
            var passVisible by remember { mutableStateOf(false) }
            OutlinedTextField(
                label = { Text("Password") },
                value = password,
                onValueChange = { password = it },
                visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconToggleButton(checked = passVisible, onCheckedChange = { passVisible = it }) {
                        Icon(
                            imageVector = if (passVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = null
                        )
                    }
                }
            )
            Button(
                onClick = { user = ""; password = "" }, enabled = isButtonEnabled
            ) {
                Text(text = "Login")
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Login") {
        App()
    }
}
