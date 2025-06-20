package br.com.cyrojrdev

import androidx.compose.runtime.Composable
import br.com.cyrojrdev.weatherApp.presentation.ui.screen.WeatherScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    WeatherScreen()
}

// @Composable
// private fun WeatherAppSample() {
//    var showContent by remember { mutableStateOf(false) }
//    Column(
//        modifier =
//            Modifier
//                .safeContentPadding()
//                .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Button(onClick = { showContent = !showContent }) {
//            Text("Click me!")
//        }
//        AnimatedVisibility(showContent) {
//            val greeting = remember { Greeting().greet() }
//            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                Image(painterResource(Res.drawable.compose_multiplatform), null)
//                Text("Compose: $greeting", color = MaterialTheme.colorScheme.primary)
//            }
//        }
//    }
// }
