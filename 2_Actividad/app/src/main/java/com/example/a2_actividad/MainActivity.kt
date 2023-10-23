package com.example.a2_actividad

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.support.v4.os.IResultReceiver.Default
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a2_actividad.ui.theme._2_ActividadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _2_ActividadTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
                }
            }
        }
    }

@Composable
fun MyApp() {

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
    }else{
        Greetings()
    }
}

@Composable
fun Greetings(names: List<String> = List(1000){"Sit"}) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column (modifier = Modifier.padding(vertical = 4.dp)){
            LazyColumn {
                item { Text(text = "Header") }
                items(names) { name ->
                    Greeting(name)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    var expanded by remember { mutableStateOf(false)}
    val extraPadding by animateDpAsState(
        targetValue = if (expanded) 48.dp else 0.dp),
        animationSpec = tween(
            durationMillis = 2000)
    )


    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row (modifier = Modifier.padding(24.dp)){

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }


            OutlinedButton(onClick = {expanded = !expanded}) {
                Text(if(expanded) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit
) {

Surface {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the Basics CodeLab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked){
            Text("Continue")
        }
    }
}
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    _2_ActividadTheme {
        OnboardingScreen (onContinueClicked = {})
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    _2_ActividadTheme {
       MyApp()
    }
}