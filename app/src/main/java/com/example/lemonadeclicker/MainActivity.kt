package com.example.lemonadeclicker

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeclicker.ui.theme.LemonadeClickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeClickerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeClicker(modifier = Modifier.fillMaxSize().padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun LemonadeClicker(modifier: Modifier = Modifier) {
    var imageState: Int by remember { mutableStateOf(0) }

    val timesToClick = (2..4).random()
    var clicked = 0

    val imageTree = painterResource(R.drawable.lemon_tree)
    val imageLemon = painterResource(R.drawable.lemon_squeeze)
    val imageFullGlass = painterResource(R.drawable.lemon_drink)
    val imageEmptyGlass = painterResource(R.drawable.lemon_restart)

    val imageText1 = stringResource(R.string.Lemon_tree)
    val imageText2 = stringResource(R.string.lemon)
    val imageText3 = stringResource(R.string.glass_of_lemonade)
    val imageText4 = stringResource(R.string.empty_glass)
    val imageList = arrayListOf(imageTree, imageLemon, imageFullGlass, imageEmptyGlass)
    val namesList = arrayListOf(imageText1, imageText2, imageText3, imageText4)

    Column(
        modifier = modifier
    ) {
        Box(
            Modifier.background(Color.Yellow).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Lemonade",
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 15.dp,bottom = 15.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = imageList[imageState],
                contentDescription = namesList[imageState],
                modifier = Modifier.clickable {
                    when(imageState) {
                        0 -> imageState++
                        1 -> {
                            clicked++
                            if(clicked >= timesToClick){
                                imageState++
                            }
                        }
                        2 -> imageState++
                        3 -> imageState = 0
                    }
                }.border(width = 2.dp,
                    color = Color(red = 105, green = 205, blue = 216),
                    shape = RoundedCornerShape(12.dp)
                ).background(color = Color(red = 195, green = 237, blue = 215),
                    shape = RoundedCornerShape(12.dp))
            )
            Spacer(
                Modifier.height(18.dp)
            )
            Text(
                text = namesList[imageState],
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeClickerTheme {
        Greeting("Android")
        LemonadeClicker(Modifier.fillMaxSize())
    }
}