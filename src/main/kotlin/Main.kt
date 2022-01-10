import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.math.BigInteger


tailrec fun fibonacci(n: Int, a: BigInteger = BigInteger("0"), b: BigInteger = BigInteger("1")): BigInteger {
    return if (n == 0) a else fibonacci(n - 1, b, a + b)
}

@Composable
@Preview
fun App() {
    var x: Int by remember { mutableStateOf(1) }
    var result by remember { mutableStateOf("fibonacci(x) = ${fibonacci(x)}") }
    MaterialTheme {
        Column {
            TextField( // TODO: revisar este componente, no funciona bien.
                label = { Text("Escriba el valor de x") },
                value = TextFieldValue(text = x.toString()),
                singleLine = true,
                onValueChange = {
                    val value = it.text.toIntOrNull()
                    x = value ?: x
                })

            Text("x = $x")

            Button(onClick = {
                val limit = 10000
                if (x <= limit) {
                    val value = fibonacci(x)
                    result = "fibonacci(x) = $value"
                } else {
                    result = "x debe ser menor o igual que $limit"
                }
            }) {
                Text("Calcular fibonacci(x)")
            }
            Text(result)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
