package com.example.sastaspotify

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@Composable
fun About( navController: NavController){
    Column(
        Modifier

            .padding(vertical = 20.dp)) {
        Row {
            Image(painter = painterResource(id = R.drawable.prev),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(horizontal = 10.dp).clickable(onClick = {navController.navigate(Screen.Setting.toString())}))
            Text(text = "About", style = MaterialTheme.typography.h5, color = Color.Black, modifier = Modifier.padding(vertical = 8.dp))
        }
        Row(Modifier.padding(vertical = 5.dp)) {
            Image(painter = painterResource(id = com.example.sastaspotify.R.drawable.dhvani_bhanushani),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(
                        CircleShape
                    ))
            Column(Modifier.padding(8.dp)) {
                Text(text = "Divyanshu Singh", style = MaterialTheme.typography.h5)
                Text(text = "CSE Student at", style = MaterialTheme.typography.h6)
                Text(text = "GLA University ", style = MaterialTheme.typography.h6  )

            }
        }
        Row() {
            Spacer(modifier = Modifier.width(12.dp))
            Image(painter = painterResource(id = R.drawable.facebool), contentDescription =null,
                Modifier
                    .clip(
                        CircleShape
                    )
                    .size(50.dp)  )
            Spacer(modifier = Modifier.width(40.dp))
            Image(painter = painterResource(id = R.drawable.insta), contentDescription =null,
                Modifier
                    .clip(
                        CircleShape
                    )
                    .size(50.dp) )
            Spacer(modifier = Modifier.width(40.dp))
            Image(painter = painterResource(id = R.drawable.github), contentDescription =null,
                Modifier
                    .clip(
                        CircleShape
                    )
                    .size(50.dp)
                    .clickable(onClick ={}))

            Spacer(modifier = Modifier.width(40.dp))
            Image(painter = painterResource(id = R.drawable.twitter), contentDescription =null,
                Modifier
                    .clip(
                        CircleShape
                    )
                    .size(50.dp) )
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}
@Composable
fun WebView(url:String){
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })

}