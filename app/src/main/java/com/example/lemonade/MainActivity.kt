package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MakeLemonade()
                }
            }
        }
    }
}

@Composable
fun MakeLemonade() {
    var currentStep by remember {mutableStateOf(1)}
    var squeezeCount by remember { mutableStateOf(0)}
    when (currentStep) {
        1 -> {
            LemonTextAndImage(textStringId = R.string.select_lemon, drawableId = R.drawable.lemon_tree,
                contentDescription = R.string.tree, onImageClick =
                {currentStep = 2
                    squeezeCount = (2..6).random()})
        }
        2 -> {
            LemonTextAndImage(textStringId = R.string.tap_lemon, drawableId = R.drawable.lemon_squeeze,
                contentDescription = R.string.lemon,
                onImageClick = {squeezeCount--
                if (squeezeCount == 0){
                        currentStep = 3}})}
        3 -> {
            LemonTextAndImage(textStringId = R.string.drink_lemonade, drawableId = R.drawable.lemon_drink,
                contentDescription = R.string.glass, onImageClick = {currentStep = 4})
        }
        4 -> {
            LemonTextAndImage(textStringId = R.string.restart, drawableId = R.drawable.lemon_restart ,
                contentDescription = R.string.empty_glass, onImageClick = {currentStep = 1})
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        MakeLemonade()
    }
}
@Composable
fun LemonTextAndImage(textStringId : Int, drawableId : Int, contentDescription : Int,
                      modifier: Modifier = Modifier, onImageClick : () -> Unit){
    Column(modifier = modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button(onClick = onImageClick, elevation = ButtonDefaults.buttonElevation(defaultElevation = 48.dp)) {
            Image(painter = painterResource(id = drawableId),
                contentDescription = stringResource(id = contentDescription))
        }
        Spacer(modifier = modifier.height(24.dp))

        Text(text = stringResource(id = textStringId), fontSize = (18.sp),
            fontWeight = FontWeight.SemiBold)
    }
}