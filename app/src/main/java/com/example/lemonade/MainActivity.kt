package com.example.lemonade

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    var currentStep by remember { mutableStateOf(1) }
    var numberOfSqueeze = 0

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> {
                LemonadeImageAndText(
                    textLabelResourceId = R.string.tap_the_lemon_tree_to_select_a_lemon,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.lemon_tree,
                    onImageClick = {
                        numberOfSqueeze = (2..4).random()
                        currentStep = 2
                    }
                )
            }

            2 -> {
                LemonadeImageAndText(
                    textLabelResourceId = R.string.keep_tapping_the_lemon_to_squeeze_it,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.lemon,
                    onImageClick = {
                        numberOfSqueeze--
                        if (numberOfSqueeze <= 0) {
                            currentStep = 3
                        }
                    }
                )
            }

            3 -> {
                LemonadeImageAndText(
                    textLabelResourceId = R.string.tap_the_lemonade_to_drink_it,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.glass_of_lemonade,
                    onImageClick = {
                        currentStep = 4
                    }
                )
            }

            4 -> {
                LemonadeImageAndText(
                    textLabelResourceId = R.string.tap_the_empty_glass_to_start_again,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.empty_glass,
                    onImageClick = {
                        numberOfSqueeze--
                        if (numberOfSqueeze <= 0) {
                            currentStep = 1
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun LemonadeImageAndText(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                modifier = Modifier
                    .wrapContentSize(),
                painter = painterResource(id = drawableResourceId),
                contentDescription = stringResource(id = contentDescriptionResourceId)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = textLabelResourceId)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}