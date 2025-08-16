package com.nugul.settle.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import com.lottiefiles.dotlottie.core.widget.DotLottieAnimation
import kotlinx.coroutines.delay
import java.nio.file.WatchEvent


@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(1f, animationSpec = tween(1500))
        delay(1000)
        // TODO : 여기서 로그인 했으면 가져올 것 로딩하기
        onSplashFinished()
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 145.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displayLarge,
                text = "너굴 정산"
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displaySmall,
                text = "어려운 정산은 너구리에게"
            )
        }


        val isDark = isSystemInDarkTheme()

        val source = if (isDark) {
            // 다크 모드용
            DotLottieSource.Asset("splash_dark24.json")
        } else {
            // 라이트 모드용
            DotLottieSource.Asset("splash_light24.json")
        }

        DotLottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(60.dp)
                .weight(1f),
            source = source,
            autoplay = true,
            loop = true,
            speed = 1f,
            useFrameInterpolation = false
        )
    }

}


