package com.soundary.last1meter

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.soundary.last1meter.ui.theme.Last1MeterTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Hearing
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // activity가 갖는 생명주기함수, acivity가 실행되자마자 호출되는 함수
        // 번들(안드)이란? https://www.crocus.co.kr/1560
        // 안드로이드에서, activity들은 데이터를 주고 받을 때 Bundle 속성의 클래스를 사용한다. by 채승운
        super.onCreate(savedInstanceState)
        setContent {
            Last1MeterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        Greeting("채승운")
                        Row {
                            StartButton()
                            EndButton()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Last1MeterTheme {
        Column {
            Greeting("채승운")
            Row {
                StartButton()
                EndButton()
            }
        }
    }
}

@Composable
fun HomeImage() {
    Icon(Icons.Rounded.Home, contentDescription = "")
}

@Composable
fun HearImage() {
    Icon(Icons.Rounded.Hearing, contentDescription = "")
}

@Composable
fun CarrotImage() {
        val image: Painter = painterResource(id = R.drawable.mainimage)
        Image(painter = image,contentDescription = "")
}

@Composable
fun Greeting(name: String) {
    Surface(
        color = androidx.compose.ui.graphics.Color.Unspecified
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CarrotImage()
            Column {
                Text(
                    text = "Welcome to last-1-meter!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp),
                    fontSize = 30.sp
                )
                Text(
                    text = "${name}님 반갑습니다! 거래자를 찾으시나요?!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun StartButton(){
   /* val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
        content = {
        Button(
            onClick = { Toast.makeText(context, "Start Finding..", Toast.LENGTH_LONG).show()},
            content = {Text(text="show test")},
            //enabled = false,
            //colors = ButtonDefaults.buttonColors(
             //   disabledBackgroundColor = Blue,
             //   disabledContentColor = White
            //),

           modifier = Modifier
               .width(210.dp)
               .height(70.dp)
        ) {
            HearImage()
            Text(text = "거래자 찾기")
        }*/

    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        content = {
            Button(onClick = {
                Toast.makeText(
                    context,
                    "거래자 탐색을 시작합니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }, content = {
                HearImage()
                Text(text = "거래자 찾기")

            },
                modifier = Modifier.width(200.dp).height(70.dp)
            )
        }, modifier = Modifier.width(200.dp).height(70.dp)

    )
}



@Composable
fun EndButton(){
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
        val context = LocalContext.current
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            content = {
                Button(onClick = {
                    Toast.makeText(
                        context,
                        "거래를 종료합니다..",
                        Toast.LENGTH_SHORT
                    ).show()
                }, content = {
                    HearImage()
                    Text(text = "거래 종료")
                },
                    modifier = Modifier.width(200.dp).height(70.dp)
                )
            }, modifier = Modifier.width(200.dp).height(70.dp).offset(x=10.dp)
        )
    }
}
