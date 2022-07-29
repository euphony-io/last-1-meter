package com.soundary.last1meter

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.soundary.last1meter.ui.theme.Last1MeterTheme
import euphony.lib.receiver.AcousticSensor
import euphony.lib.receiver.EuRxManager
import euphony.lib.transmitter.EuTxManager

class MainActivity : ComponentActivity() {

    fun checkPermission() {
        // 1. 위험권한(Camera) 권한 승인상태 가져오기
        val AudioPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        if (AudioPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermission()
        } else {
            startProcess()
        }
    }

    // 2. 권한 요청
    fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 99)
    }

    // 권한 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            99 -> {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.d("MainActivity", "종료")
                }

            }
        }
    }

    fun startProcess() {
        Toast.makeText(this, "오디오 기능 실행", Toast.LENGTH_SHORT).show()
    }


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
                    Column{
                        Greeting("Euphony!")
                        checkPermission()
                        MyContent()
                    }
                }
            }
        }
    }
}


@Composable
fun CreateDialog(onClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
        },
        title = {
            Text(text = "종료")
        },
        text = {

            Text("애플리케이션을 종료하시겠습니까? ")
        },
        confirmButton = {
            Button(
                onClick = {
                    System.exit(0)
                }
            )
            {
                Text("확인")
            }
        },
        dismissButton = {
            Button(
                onClick
            )
            {
                Text("취소")
            }
        }
    )
}

@Composable
fun CreateButton(icon : ImageVector,text:String, onClick: ()-> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(155.dp)
            .height(70.dp)
    ){
        Icon(icon, contentDescription = "")
        Text(text = text)

    }
}


private fun CreateToast(context: Context, text : String){
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}


@Composable
fun MyContent() {
    val mTxManager = EuTxManager()
    val mRxManager = EuRxManager()
    val context = LocalContext.current

    Row(horizontalArrangement = Arrangement.Center) {

        val exitClickable = remember { mutableStateOf(false) }

        CreateButton(Icons.Rounded.Home, text = "거래자 찾기") {
            transmitter(mTxManager, mRxManager) // test
            receiver(mRxManager, mTxManager) // test
            CreateToast(context, "거래자 탐색을 시작합니다")
        }

        Spacer(modifier = Modifier.width(100.dp))

        CreateButton(Icons.Rounded.ExitToApp, text = "거래 종료") {
            exitClickable.value = true
        }

        if (exitClickable.value) {
            CreateDialog() {
                exitClickable.value = false
            }
        }
    }
        CreateDis(10)


}

@Composable
fun CreateDis(dis : Int){
    Surface( // 색깔 지정
        color = androidx.compose.ui.graphics.Color.Unspecified
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column() {
                Text( // 텍스트의 내용 및 크기, 특성 등을 지정
                    text = "${dis}m",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp)
                        .offset(x = 140.dp),
                    fontSize = 100.sp
                )
                Text( // 텍스트의 내용 및 크기, 특성 등을 지정
                    text = "남았습니다.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp)
                        .offset(x = 100.dp),
                    fontSize = 60.sp
                )
            }

        }
    }
}

@Composable
fun CarrotImage() {
    val image: Painter = painterResource(id = R.drawable.mainimage)
    Image(painter = image,contentDescription = "") // 당근이 이미지 삽입
}

@Composable
fun Greeting(name: String) {
    Surface( // 색깔 지정
        color = androidx.compose.ui.graphics.Color.Unspecified
    ) { // 행의 규칙으로
        Row(verticalAlignment = Alignment.CenterVertically) {
            CarrotImage() // 이미지 삽입
            Column { // 오른쪽에 텍스트들이 삽입되는데 이것들은 열의 규칙으로
                Text( // 텍스트의 내용 및 크기, 특성 등을 지정
                    text = "Welcome to last-1-meter!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp),
                    fontSize = 30.sp
                )
                Text( // 텍스트의 내용 및 크기, 특성 등을 지정
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

fun transmitter(mTxManager: EuTxManager, mRxManager: EuRxManager) {
    val mTxManager = EuTxManager()
    mTxManager.euInitTransmit("Hello, Euphony") // To generate acoustic data "Hello, Euphony"
    mTxManager.process(-1) // generate sound infinite.
}

fun receiver(mRxManager: EuRxManager, mTxManager: EuTxManager) {
    val mRxManager = EuRxManager()
    mRxManager.acousticSensor = AcousticSensor {
        //when data is received
    }
    mRxManager.listen() //Listening Start
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Last1MeterTheme {
        Greeting(name = "Euphony")
        MyContent()
    }
}
