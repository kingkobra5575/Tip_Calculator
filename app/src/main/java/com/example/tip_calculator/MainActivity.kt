package com.example.tip_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.tip_calculator.ui.theme.TIp_CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TIp_CalculatorTheme {
                MyScreen()
            }
        }
    }
}

@Composable
fun MyScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        var amt by remember {
            mutableStateOf("")
        }

        Text(
            text="Tip Calculator",
            color = White,
            fontSize = 25.sp
        )

        Text(text = "")
        
        OutlinedTextField(
            value =amt ,
            onValueChange = {
                if(it.isNumeric() || it.isBlank()){
                    amt=it
                }
            },
            label={Text("Enter Bill Amount", color = Color.White)
            }
        )

        val amount=amt.toDoubleOrNull()?:0.0
        val tip= calculatetip(amount)

        Text(text = "")
        
        Text(
            text="Tip to Pay: ₹"+tip,
            fontSize = 17.sp,
            color = White,
            fontWeight = FontWeight.Bold
        )
    }

}

private fun String.isNumeric():Boolean{
    return this.toDoubleOrNull()!=null;
}

private fun calculatetip(amount:Double, tipPer:Double=15.0):Double{
    val tip=amount*tipPer/100;
    return tip;
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    TIp_CalculatorTheme {
        MyScreen()
    }
}