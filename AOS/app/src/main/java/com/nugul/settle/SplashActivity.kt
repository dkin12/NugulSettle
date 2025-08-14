package com.nugul.settle

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.nugul.settle.ui.theme.NugulSettleUpTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NugulSettleUpTheme {
                SplashScreen()
            }
        }
    }

    @Preview
    @Composable
    fun SplashScreen(

    ){
        val alpha = remember {
            Animatable(0f)
        }
        LaunchedEffect(key1 = Unit) {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(1500)
            )
            delay(2000L)

            Intent(this@SplashActivity, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }.let { intent ->
                startActivity(intent)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Image(
                painter = painterResource(id = R.drawable.group_list_item_face),
                contentDescription = "이미지",
                modifier = Modifier.alpha(alpha.value)
            )
        }
    }

}