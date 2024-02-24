package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
                Surface() {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {

    var result by remember {
        mutableStateOf(1)
    }

    var tapCounter by remember { mutableStateOf(0) }

    Text(
        text = "Lemonade",
        Modifier
            .background(Color.Yellow)
            .fillMaxWidth()
            .padding(16.dp),
        fontSize = 26.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold

    )

    when (result) {
        1 -> {
            GenerateTextAndImage(
                imageResource = R.drawable.lemon_tree,
                imageTxtResource = R.string.ImageTxtLemonTree,
                textResource = R.string.TapLemonTree,
                onImageClick = {
                    tapCounter = (2..4).random()
                    result = 2
                })

        }

        2 -> {
            GenerateTextAndImage(
                imageResource = R.drawable.lemon_squeeze,
                imageTxtResource = R.string.ImageTxtLemon,
                textResource = R.string.TapLemon,
                onImageClick = {
                    tapCounter--

                    if(tapCounter==0){
                        result = 3
                    }

                })

        }

        3 -> {
            GenerateTextAndImage(
                imageResource = R.drawable.lemon_drink,
                imageTxtResource = R.string.ImageTxtLemonade,
                textResource = R.string.TapLemonade,
                onImageClick = { result = 4 })

        }

        else -> {
            GenerateTextAndImage(
                imageResource = R.drawable.lemon_restart,
                imageTxtResource = R.string.ImageTxtEmptyGlass,
                textResource = R.string.TapGlass,
                onImageClick = { result = 1 })

        }
    }





}


@Composable
fun GenerateTextAndImage(
    imageResource: Int,
    imageTxtResource: Int,
    textResource: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Button(
                onClick =  onImageClick ,
                colors = ButtonDefaults.buttonColors(colorResource(R.color.teal_700)) ,
                //modifier = Modifier.border(2.dp, colorResource(R.color.ButtonColor), RoundedCornerShape(4.dp))
            ){
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = stringResource(imageTxtResource),

                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(textResource),
                style = MaterialTheme.typography.bodyLarge


                )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeApp()
}