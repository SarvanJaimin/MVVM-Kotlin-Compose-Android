package com.jam.mvvmdemojaiminsarvan

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class TopActivity : ComponentActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetOptions()
        }
    }

    @Preview
    @Composable
    fun GetOptions(){
        Column( modifier = Modifier.padding(4.dp).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Button(
                modifier = Modifier.padding(16.dp).width(200.dp).height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.White),
                onClick = {
                    val intent = Intent(this@TopActivity, MainActivity::class.java)
                    startActivity(intent)
                }){
                Text("Kotlin MVVM")
            }

            Button(
                modifier = Modifier.padding(16.dp).width(200.dp).height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.White),
                onClick = {
                    val intent = Intent(this@TopActivity, TopHeadLineActivity::class.java)
                    startActivity(intent)
                }){
                Text("Compose MVVM")
            }

            Button(
                modifier = Modifier.padding(16.dp).width(200.dp).height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.White),
                onClick = {
                    val intent = Intent(this@TopActivity, SearchTopHeadLineActivity::class.java)
                    startActivity(intent)
                }){
                Text("Search")
            }

        }
    }
}