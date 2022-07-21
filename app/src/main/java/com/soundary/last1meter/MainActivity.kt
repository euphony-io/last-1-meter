package com.soundary.last1meter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.soundary.last1meter.ui.theme.Last1MeterTheme

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
                    Greeting("world!")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Last1MeterTheme {
        Greeting("Euphony")
    }
}